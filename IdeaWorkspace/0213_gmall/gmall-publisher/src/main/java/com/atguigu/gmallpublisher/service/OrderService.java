package com.atguigu.gmallpublisher.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    // 以下为新增
    /**
     * 销售额总数
     * @param date
     * @return
     */
    double getOrderAmountTotal(String date);

    /**
     * 获取销售额小时明细
     * @param date
     * @return
     */
    Map getOrderAmountHour(String date);



}
