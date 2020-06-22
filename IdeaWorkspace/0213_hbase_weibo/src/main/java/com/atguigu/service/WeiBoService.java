package com.atguigu.service;

import com.atguigu.constant.Names;
import com.atguigu.dao.WeiBoDao;

import javax.naming.Name;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiBoService {
    private WeiBoDao weiBoDao= new WeiBoDao();
    public void init() throws IOException {
        //1) 创建命名空间以及表名的定义
        weiBoDao.createNameSpace(Names.NAMESPACE_WEIBO);
        //2) 创建微博内容表
        weiBoDao.createTable(Names.TABLE_WEIBO,1,Names.WEIBO_FAMILY_DATA);
        //3) 创建用户关系表
        weiBoDao.createTable(Names.TABLE_RELATION,1,Names.RELATION_FAMILY_DATA);
        //4) 创建用户微博内容接收邮件表
        weiBoDao.createTable(Names.TABLE_INBOX,Names.INBOX_VERSIONS,Names.INBOX_FAMILY_DATA);
    }

    /**
     * 发微博
     * @param star
     * @param content
     */
    public void publish(String star, String content)throws Exception {
      //1.在微博表中插入一条数据
        String rowKey=star+"_"+System.currentTimeMillis();
        weiBoDao.putCell(Names.TABLE_WEIBO,rowKey,Names.WEIBO_FAMILY_DATA,Names.WEIBO_COLUMN_CONTENT,content);

        //2查找start的所有fansId
        String prefix=star+":followedby:";
        List<String> list=weiBoDao.getRowKeysByPrefix(Names.TABLE_RELATION,prefix);
        if (list.size()==0) {
            return;
        }
        //截取所有的fansId
        List<String> fansList=new ArrayList<String>();
        for (String row : list) {
            String[] split = row.split(":");
            fansList.add(split[2]);
        }
        //3.批量将微博Id插入所有的fans的box中
        weiBoDao.putCells(Names.TABLE_INBOX,fansList,Names.INBOX_FAMILY_DATA,star,rowKey);


    }

    /**
     * 关注用户
     * @param fans
     * @param star
     */
    public void follow(String fans, String star) throws IOException {
        //1.往relation插入两条数据
        String rowKey1=fans+":follow:"+star;
        String rowKey2=star+":followedby:"+fans;
        String time=System.currentTimeMillis()+"";
        weiBoDao.putCell(Names.TABLE_RELATION,rowKey1,Names.RELATION_FAMILY_DATA,Names.RELATION_COLUMN_TIME,time);
        weiBoDao.putCell(Names.TABLE_RELATION,rowKey2,Names.RELATION_FAMILY_DATA,Names.RELATION_COLUMN_TIME,time);
        //2.从微博表中获取近期的微博的id
        String startRow=star+"_";
        //匹配
        String stopRow=star+"_|";
        List<String> list=weiBoDao.getRowKeysByRange(Names.TABLE_WEIBO,startRow,stopRow);
        if (list.size()<=0){
            return;
        }
        //判断是否发过三条微博
        int fromIndex=list.size()>=3 ? list.size()-Names.INBOX_VERSIONS : 0;
        //获取list的前五条
        List<String> inboxlist= list.subList(fromIndex, list.size());
        //3.将获取的近期微博id插入fans的inbox中
        for (String rweiboId : inboxlist) {
            weiBoDao.putCell(Names.TABLE_INBOX,fans,Names.INBOX_FAMILY_DATA,star,rweiboId);
        }
    }

    /**
     * 取关
     * @param fans
     * @param star
     */
    public void unFollow(String fans, String star) throws IOException {
        //1.删除relation表中的两条是数据
        String rowKey1=fans+":follow:"+star;
        String rowKey2=star+":followedby:"+fans;
        weiBoDao.deleteRow(Names.TABLE_RELATION,rowKey1);
        weiBoDao.deleteRow(Names.TABLE_RELATION,rowKey2);
        //2.删除inbox表中️的fans行的start列
        weiBoDao.deleteColunm(Names.TABLE_INBOX,fans,Names.INBOX_FAMILY_DATA,star);
    }

    /**
     * 查询关注明星的所有微博
     * @param tableName
     * @param prefix
     * @param family
     * @param colunm
     * @return
     */
    public List<String> getCellsbByPerfix(String tableName, String prefix, String family, String colunm) throws IOException {
       return weiBoDao.getCellsbByPerfix(tableName,prefix,family,colunm);
    }

    /**
     * 获取某个fans关注的所有star的近期微博
     * @param fans
     * @return
     */
    public List<String> getAllRecentWeiBos(String fans) throws IOException {
        //1.从inbox中获取粉丝的所有start的近期微博id
        List<String> list= weiBoDao.getRow(Names.TABLE_INBOX,fans);
        //2.根据近期微博id，批量从微博表中获取相应的微博
        return weiBoDao.getCellsByRowKeys(Names.TABLE_WEIBO,list, Names.WEIBO_FAMILY_DATA,Names.WEIBO_COLUMN_CONTENT);
    }
}
