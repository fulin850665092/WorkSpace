package com.hengyangshiyuan.hrsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.dao.CommodityDao;
import com.hengyangshiyuan.hrsystem.service.CommodityService;

@Service
public class CommodityServiceImpl implements CommodityService{
	
	@Autowired
	private CommodityDao commodityDao;
	
	/**
	 * 查询所有dispaly的商品
	 */
	@Override
	public List<Commodity> queryAllByDisplay(String display) {
		// TODO Auto-generated method stub
		return commodityDao.queryAllByDisplay(display);
	}
	
	/**
	 * 
	 */
	@Override
	public List<Commodity> queryAllCommodity() {
		// TODO Auto-generated method stub
		return commodityDao.queryAllCommodity();
	}

	@Override
	public Double queryCount() {
		// TODO Auto-generated method stub
		return commodityDao.queryCount();
	}
	public Page<Commodity> page(int pageNo, int pageSize) {
		Page<Commodity> page = new Page<Commodity>();
		
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = commodityDao.queryForPageTotalCount();
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 总页码由 总记录数 / 每页显示的数量可得
		int pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 注意，判断，是否可以除尽
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		
		//设置当前页码
		page.setPageNo(pageNo);
		// 当前页要显示的数据的开始索引
		int begin = (page.getPageNo()-1) * page.getPageSize();
		List<Commodity> items = commodityDao.queryForPageItems(begin,page.getPageSize());
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	public Page<Commodity> pageManager(int pageNo, int pageSize) {
		Page<Commodity> page = new Page<Commodity>();
		
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = commodityDao.queryManagerForPageTotalCount();
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 总页码由 总记录数 / 每页显示的数量可得
		int pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 注意，判断，是否可以除尽
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		
		//设置当前页码
		page.setPageNo(pageNo);
		// 当前页要显示的数据的开始索引
		int begin = (page.getPageNo()-1) * page.getPageSize();
		List<Commodity> items = commodityDao.queryForPageItems(begin,page.getPageSize());
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	@Override
	public Page<Commodity> queryByType(int pageNo, int pageSize, String type) {
		
		Page<Commodity> page = new Page<Commodity>();
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = commodityDao.queryForPageTotalCountByType(type);
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 总页码由 总记录数 / 每页显示的数量可得
		int pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 注意，判断，是否可以除尽
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		
		//设置当前页码
		page.setPageNo(pageNo);
		// 当前页要显示的数据的开始索引
		int begin = (page.getPageNo()-1) * page.getPageSize();
		List<Commodity> items = commodityDao.queryByType(begin,page.getPageSize(),type);
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	
	@Override
	public Page<Commodity> getNewCommodity(int pageNo, int pageSize) {
		
		Page<Commodity> page = new Page<Commodity>();
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = 20;
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 总页码由 总记录数 / 每页显示的数量可得
		int pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 注意，判断，是否可以除尽
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		
		//设置当前页码
		page.setPageNo(pageNo);
		// 当前页要显示的数据的开始索引
		int begin = (page.getPageNo()-1) * page.getPageSize();
		List<Commodity> items = commodityDao.getNewCommodity(begin,page.getPageSize());
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	@Override
	public Map<String, Object> getCommodityDetails(String labelId) {
		// TODO Auto-generated method stub
		return commodityDao.getCommodityDetails(labelId);
	}

	@Override
	public Page<Commodity> queryBySerach(int pageNo, int pageSize, String name) {
		Page<Commodity> page = new Page<Commodity>();
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = commodityDao.queryForPageTotalCountBySerach(name);
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 总页码由 总记录数 / 每页显示的数量可得
		int pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 注意，判断，是否可以除尽
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		
		//设置当前页码
		page.setPageNo(pageNo);
		// 当前页要显示的数据的开始索引
		int begin = (page.getPageNo()-1) * page.getPageSize();
		List<Commodity> items = commodityDao.queryBySerach(begin,page.getPageSize(),name);
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}

	@Override
	public Commodity queryCommodityById(int id) {
		// TODO Auto-generated method stub
		return commodityDao.queryCommodityById(id);
	}

	@Override
	public int queryCountByLabelId(String labelId) {
		// TODO Auto-generated method stub
		return commodityDao.queryCountByLabelId(labelId);
	}

	@Override
	public void updataCountByLabelId(String labelId, int countUpd) {
		// TODO Auto-generated method stub
		commodityDao.updataCountByLabelId(labelId,countUpd);
	}

	@Override
	public void updateCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		commodityDao.updateCommodity(commodity);
	}

	@Override
	public void insertCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		commodityDao.insertCommodity(commodity);
	}
	

}
