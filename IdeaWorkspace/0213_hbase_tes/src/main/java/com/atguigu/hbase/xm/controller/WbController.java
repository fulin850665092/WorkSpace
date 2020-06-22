package com.atguigu.hbase.xm.controller;

import com.atguigu.hbase.xm.constan.Names;
import com.atguigu.hbase.xm.service.WbService;

import java.util.List;

public class WbController {
    private WbService wbService= new WbService();

    /**
     * 初始化
     * @throws Exception
     */
    public  void init() throws Exception{
        wbService.init();
    }

    /**
     * 发布微博
     * @param start
     * @param content
     * @throws Exception
     */
    public void  publish(String start,String content) throws Exception{

       wbService.pubWeibo(start,content);
    }

    /**
     * 关注
     * @param fans
     * @param star
     * @throws Exception
     */
    public  void follow(String fans,String star)throws Exception{
        wbService.follow(fans,star);
    }

    /**
     * 取关
     * @param fans
     * @param star
     * @throws Exception
     */
    public  void unFollow(String fans,String star)throws Exception{
        wbService.unFollow(fans,star);
    }
    //获取所以关注的人的微博
    //获取某个star的所有微博
    public List<String> getAllWeibosByStarId(String fans, String star)throws Exception{
        return  wbService.getCellsbByPerfix(Names.TABLE_WEIBO,star,Names.WEIBO_FAMILY_DATA,Names.WEIBO_COLUMN_CONTENT);
    }
    //获取某个fans关注的所有star的近期微博
    public  List<String> getRecentWeibosByFansId(String fans,String star)throws Exception{

        return wbService.getRecentWeibosByFansId(fans);
    }
}
