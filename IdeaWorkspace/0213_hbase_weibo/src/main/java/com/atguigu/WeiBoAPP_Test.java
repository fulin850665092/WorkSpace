package com.atguigu;

import com.atguigu.controller.WeiBoController;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class WeiBoAPP_Test {

    private static WeiBoController controller= new WeiBoController();
    /**
     * 初始化
     * @throws IOException
     */
    @Test
    public void init() throws IOException {
        //初始化，创建namespace，表
        controller.init();
    }

    /**
     * 关注
     * @throws IOException
     */
    @Test
    public void follow() throws IOException {
        //关注
        controller.follow("1001","1002");
        controller.follow("1001","1003");
        controller.follow("1001","1004");
    }
    /**
     * 取关
     * @throws IOException
     */
    @Test
    public void unFollow() throws IOException {
        //取关
        controller.unFollow("1001","1004");
    }
    @Test
    public void publish() throws Exception {
        //发布微博
        controller.publish("1002","恍如隔日，三杯凉酒，暖肚虫！");
        controller.publish("1002","一吻便救一个人！");
        controller.publish("1002","给你拯救的体温！");
        controller.publish("1002","总会再捐给某人！");
        controller.publish("1002","一吻便偷一个心！");
        controller.publish("1002","一吻便杀一个人！");
        controller.publish("1002","一串吻感一串金！");
        controller.publish("1002","一脸崎岖的旅行！");

        controller.publish("1003","你爱热吻却永不爱人！");
        controller.publish("1003","练习为乐但是怕熟人！");
        controller.publish("1003","你爱路过去索取见闻！");
        controller.publish("1003","陌路人变得必有份好感！");
        controller.publish("1003","你亦爱别离再合再离！");
        controller.publish("1003","似花瓣献技 叫花粉遍地 噢噢！");
        controller.publish("1003","你在播弄这穿线游戏！");
        controller.publish("1003","跟他结束她与他再一起！");
        controller.publish("1003","你小心一吻便颠倒众生！");
    }

    /**
     * 查看明星的微博
     * @throws IOException
     */
    @Test
    public void getWeiBosByStarId() throws IOException {
        //查看明星的微博
        List<String> weiBosByStarId = controller.getWeiBosByStarId("1002");
        if (weiBosByStarId.size()>=1){
            for (String weibo : weiBosByStarId) {

                System.out.println(weibo);
            }
        }
    }

    /**
     * 查看关注的明星的近期的微博
     * @throws IOException
     */
    @Test
    public void getAllRecentWeiBos() throws IOException {
        List<String> allRecentWeiBos = controller.getAllRecentWeiBos("1001");
        if (allRecentWeiBos.size()>=1){
            for (String allRecentWeiBo : allRecentWeiBos) {

                System.out.println(allRecentWeiBo);
            }
        }
    }
}
