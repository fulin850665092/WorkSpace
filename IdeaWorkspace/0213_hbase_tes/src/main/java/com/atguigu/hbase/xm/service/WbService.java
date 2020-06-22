package com.atguigu.hbase.xm.service;

import com.atguigu.hbase.xm.constan.Names;
import com.atguigu.hbase.xm.dao.WbDao;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;


public class WbService {
    private WbDao wbDao=new WbDao();

    public void init() throws Exception {

//       1) 创建命名空间以及表名的定义
        wbDao.createNameSapce();
//       2) 创建微博内容表
        wbDao.createTable(Names.TABLE_WEIBO,Names.WEIBO_VERSIONS,Names.WEIBO_FAMILY_DATA);
//       3) 创建用户关系表
        wbDao.createTable(Names.TABLE_RELATION,Names.RELATION_VERSIONS,Names.RELATION_FAMILY_DATA);
//       4) 创建用户微博内容接收邮件表
        wbDao.createTable(Names.TABLE_INBOX,Names.INBOX_VERSIONS,Names.INBOX_FAMILY_DATA);

    }

    public void pubWeibo(String start, String content) throws Exception {
        String rowKey=start+"_"+System.currentTimeMillis();
        //向微博表插入数据
        wbDao.putCell(Names.TABLE_WEIBO,rowKey,Names.WEIBO_FAMILY_DATA,Names.WEIBO_COLUMN_CONTENT,content);

        //查询star的所有fansId
        String prefix=start+":followedby:";
        List<String> rowKeyList = wbDao.getRowKeysByPreFix(Names.TABLE_RELATION, prefix);
        //判空
        if (rowKeyList.size()<=0){
            return;
        }
        List<String> fansList=new ArrayList<String>();
        for (String rk : rowKeyList) {
            String[] split = rk.split(":");
            fansList.add(split[2]);
        }

        //将weiboID插入到fans的inbox中
        wbDao.putCells(Names.TABLE_INBOX,fansList,Names.INBOX_FAMILY_DATA,start,rowKey);
    }

    public void follow(String fans, String start) throws Exception {
        //向关系表中 插入两条数据
        String startRowKey=start+":followed:"+fans;
        String time=System.currentTimeMillis()+"";
        wbDao.putCell(Names.TABLE_RELATION,startRowKey,Names.RELATION_FAMILY_DATA,Names.RELATION_COLUMN_TIME,time);
        String fansRowKey=fans+":follow:"+start;
        wbDao.putCell(Names.TABLE_RELATION,fansRowKey,Names.RELATION_FAMILY_DATA,Names.RELATION_COLUMN_TIME,time);

        //从微博表中获取star近期的微博Id
        String startRow=start+"_";
        String stopRow=start+"_|";
        List<String> rowKeysList = wbDao.getRowKeysByRange(Names.TABLE_WEIBO, startRow, stopRow);
        if (rowKeysList.size()<=0){
            return;
        }
        int startSize= rowKeysList.size()>=Names.INBOX_VERSIONS ? rowKeysList.size()-Names.INBOX_VERSIONS:0;
        List<String> recentWeiboIds = rowKeysList.subList(startSize, rowKeysList.size());

        //将star的近期weiboId插入到fans的inbox
        for (String royKey : recentWeiboIds) {
            wbDao.putCell(Names.TABLE_INBOX,fans,Names.INBOX_FAMILY_DATA,start,royKey);
        }
    }

    public void unFollow(String fans, String start) throws  Exception{
        //删除关系表的两条数据
        String startRowKey=start+":followed:"+fans;
        wbDao.deleteRow(Names.TABLE_RELATION,startRowKey);
        String fansRowKey=fans+":follow:"+start;
        wbDao.deleteRow(Names.TABLE_RELATION,fansRowKey);
        //删除inbox中fans关注的star的列微博id
        wbDao.deleteColunm(Names.TABLE_INBOX,fans,Names.INBOX_FAMILY_DATA,start);
    }

    public List<String> getCellsbByPerfix(String tableName, String prefix, String family, String column) throws Exception {
       return wbDao.getCellsbByPerfix(tableName,prefix,family,column);
    }

    public List<String> getRecentWeibosByFansId(String fans) throws  Exception{

        //从inbox 中 获取fans的所有star的近期weibo 的id
        List<String> recentWeiboIds = wbDao.getRow(Names.TABLE_INBOX, fans);
        if (recentWeiboIds.size()==0){
            return new ArrayList<String>();
        }
        //从weibo表获取所有的微博
       return  wbDao.getCellByRowkeys(Names.TABLE_WEIBO,recentWeiboIds,Names.WEIBO_FAMILY_DATA, Names.WEIBO_COLUMN_CONTENT);

    }
}
