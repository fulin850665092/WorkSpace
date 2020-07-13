package com.hengyangshiyuan.hrsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Commodity;
import com.hengyangshiyuan.hrsystem.bean.Order;
import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.dao.ManagerDao;
import com.hengyangshiyuan.hrsystem.dao.OrderDao;
import com.hengyangshiyuan.hrsystem.service.ManagerService;
import com.hengyangshiyuan.hrsystem.service.OrderService;
import com.hengyangshiyuan.hrsystem.service.UserService;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Override
	public String getType(int userId) {
		// TODO Auto-generated method stub
		return managerDao.getType(userId);
	}
	@Override
	public Page<Order> page(int pageNo, int pageSize) {
		Page<Order> page = new Page<Order>();
		
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = orderService.queryForPageTotalCount();
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
		List<Order> items = managerDao.queryForPageItems(begin,page.getPageSize());
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	@Override
	public Page<User> userpage(int pageNo, int pageSize) {
		Page<User> page = new Page<User>();
		
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = userService.queryForPageTotalCount();
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
		List<User> items = managerDao.queryUserForPageItems(begin,page.getPageSize());
		// 设置当前页要显示的数据
		page.setItems(items);
		
		return page;
	}
	@Override
	public String queryGongGao(String usertype) {
		// TODO Auto-generated method stub
		return managerDao.queryGongGao(usertype);
	}
}
