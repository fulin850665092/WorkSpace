package com.atguigu.gmallpublisher.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DauMapper {
    public long getDauTotal(String date);

    public List<Map> getDauHour(String date);
}
