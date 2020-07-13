package com.hengyangshiyuan.hrsystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Page;




public interface CommodityService {

	List<Commodity> queryAllByDisplay(String display);
	List<Commodity> queryAllCommodity();
	Double queryCount();
	Page<Commodity> page(int pageNo, int pageSize);
	Page<Commodity> queryByType(int pageNo, int pageSize, String type);
	Map<String, Object> getCommodityDetails(String labelId);
	Page<Commodity> queryBySerach(int pageNo, int pageSize, String name);
	Commodity queryCommodityById(int id);
	int queryCountByLabelId(String labelId);
	void updataCountByLabelId(String labelId, int countUpd);
	Page<Commodity> getNewCommodity(int pageNo, int pageSize);
	Page<Commodity> pageManager(int pageNo, int pageSize);
	void updateCommodity(Commodity commodity);
	void insertCommodity(Commodity commodity);	
	
}	
