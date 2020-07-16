package com.atguigu.gmallpublisher.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmallpublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PublisherController {
    @Autowired
    private  PublisherService publisherService;
    private final static String DAU_ID= "dau";
    @GetMapping("realtime-total")
    public String getRealtimeTotal(@RequestParam("date") String date) {
        List<Map> totalList = new ArrayList<>();

        Map dauMap = new HashMap<>();
        dauMap.put("id", "dau");
        dauMap.put("name", "新增日活");
        dauMap.put("value", publisherService.getDauTotal(date));
        totalList.add(dauMap);

        Map midMap = new HashMap<>();
        midMap.put("id", "new_mid");
        midMap.put("name", "新增设备");
        midMap.put("value", "1000");
        totalList.add(midMap);
        return JSON.toJSONString(totalList);
    }

    @GetMapping("realtime-hour")
    public String getRealtimeHour(@RequestParam("id") String id, @RequestParam("date") String date){
        if(DAU_ID.equals(id)){
            Map todayDauHourMap = publisherService.getDauHour(date);
            Map yesterdayDauHourMap = publisherService.getDauHour(getYesterday(date));

            Map<String, Map> hourMap = new HashMap<>();
            hourMap.put("today", todayDauHourMap);
            hourMap.put("yesterday", yesterdayDauHourMap);
            return JSON.toJSONString(hourMap);
        }
        return null;
    }



    /**
     * 根据传入的日期转换成昨天
     *
     * @param date
     * @return
     */
    public  String getYesterday(String date) {
        return LocalDate.parse(date).minusDays(1).toString();
    }

//    public static void main(String[] args) {
//        System.out.println(getYesterday("2020-07-15"));
//    }

}
