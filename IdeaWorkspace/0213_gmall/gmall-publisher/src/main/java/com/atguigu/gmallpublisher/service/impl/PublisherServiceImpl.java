package com.atguigu.gmallpublisher.service.impl;

import com.atguigu.gmallpublisher.mapper.DauMapper;
import com.atguigu.gmallpublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private DauMapper dauMapper;

    @Override
    public long getDauTotal(String date) {
        return dauMapper.getDauTotal( date);
    }

    @Override
    public Map getDauHour(String date) {
        List<Map> dauHourList = dauMapper.getDauHour(date);

        Map dauHourMap = new HashMap();
        for (Map map : dauHourList) {
            String hour = (String)map.get("LOGHOUR");
            Long count = (Long) map.get("COUNT");
            dauHourMap.put(hour, count);
        }

        return dauHourMap;
    }

}
