package com.atguigu.gmallpublisher.service;

import java.util.List;
import java.util.Map;

public interface PublisherService {
    /**
     * 查询日活总数
     * @param date
     * @return long
     */
    long getDauTotal(String date);
    /**
     * 查询小时明细
     * @param date
     * @return Map
     */
    Map getDauHour(String date);
}
