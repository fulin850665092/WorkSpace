package com.hengyangshiyuan.hrsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Page;

@Mapper
public interface CommodityDao {

	List<Commodity> queryAllByDisplay(String display);
	List<Commodity> queryAllCommodity();
	Double queryCount();
	int queryForPageTotalCount();
	// 求当前页要显示的数据
	public List<Commodity> queryForPageItems(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize);
	//根据类型查询
	List<Commodity> queryByType(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize, @Param(value="type")String type);
	int queryForPageTotalCountByType(String type);
	Map<String, Object> getCommodityDetails(String labelId);
	int queryForPageTotalCountBySerach(String serach);
	List<Commodity> queryBySerach(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize, @Param(value="name")String name);
	Commodity queryCommodityById(int id);
	int queryCountByLabelId(String labelId);
	void updataCountByLabelId(@Param(value="labelId")String labelId, @Param(value="countUpd")int countUpd);
	List<Commodity> getNewCommodity(@Param(value="begin")int begin, @Param(value="pageSize")int pageSize);
	int queryManagerForPageTotalCount();
	void updateCommodity(Commodity commodity);
	void insertCommodity(Commodity commodity);
	

	
}
