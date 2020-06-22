package com.atguigu.controller;

import com.atguigu.constant.Names;
import com.atguigu.service.WeiBoService;

import java.io.IOException;
import java.util.List;

public class WeiBoController {

    private WeiBoService service= new WeiBoService();

    /**
     * 初始化
     */
    public void init() throws IOException {
        service.init();
    }

    //5) 发布微博内容
    public void  publish(String star,String content) throws Exception {
        service.publish( star, content);
    }

    //6) 添加关注用户
    public void follow(String fans,String star) throws IOException {
        service.follow(fans,star);
    }

    //7) 移除（取关）用户
    public  void  unFollow(String fans,String star) throws IOException {
        service.unFollow(fans,star);
    }

    //8) 获取关注的人的微博内容rowKey
    //8.1获取某个star的所有微博
    public List<String> getWeiBosByStarId(String star) throws IOException {
        return  service.getCellsbByPerfix(Names.TABLE_WEIBO,star,Names.WEIBO_FAMILY_DATA,Names.WEIBO_COLUMN_CONTENT);
    }

    //8.2 获取某个fans关注的所有star的近期微博
    public List<String> getAllRecentWeiBos(String fans) throws IOException {
        return service.getAllRecentWeiBos(fans);
    }

}
