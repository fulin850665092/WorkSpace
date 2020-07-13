package com.hengyangshiyuan.hrsystem.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;
import com.hengyangshiyuan.hrsystem.dao.UserDao;
import com.hengyangshiyuan.hrsystem.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public void regist(User user) {
		userDao.saveUser(user);
	}

	@Override
	public boolean existUsername(String username) {
		User user = userDao.queryUserByUsername(username);
		// 如果返回的user为null，说明没有在数据库中找到给定用户名的用户信息。也就是用户名可用
		if (user == null) {
			// 用户名可用
			return false;
		} else {
			// 用户名已存在
			return true;
		}
	}

	

	@Override
	public User queryUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.queryUserByUsernameAndPassword(username, password);
	}

	@Override
	public User queryUserIdByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.queryUserByUsername(username);
	}

	@Override
	public String queryLastDate(int id) {
		// TODO Auto-generated method stub
		return userDao.queryLastDate(id);
	}

	@Override
	public void UpdateLastDate(String date,int id) {
		// TODO Auto-generated method stub
		userDao.UpdateLastDate(date,id);
	}

	@Override
	public void updateUser(int id,Date brithday, String sex, String email, String phone) {
		userDao.updateUser(id,brithday,sex,  email, phone);
		
	}

	@Override
	public void updePassword(int id, String newpasswod) {
		// TODO Auto-generated method stub
		userDao.updePassword(id,newpasswod);
	}

	@Override
	public int queryForPageTotalCount() {
		// TODO Auto-generated method stub
		return userDao.queryForPageTotalCount();
	}

	@Override
	public void managerUpdateUser(User paramUser) {
		// TODO Auto-generated method stub
		userDao.managerUpdateUser(paramUser);
	}

	@Override
	public void deleteUser(int paramId) {
		// TODO Auto-generated method stub
		userDao.deleteUser(paramId);
	}

	@Override
	public void updateGongGao(String gonggao, String usertype) {
		// TODO Auto-generated method stub
		userDao.updateGongGao(gonggao,usertype);
	}

	@Override
	public int quseryByTypeAdmin() {
		// TODO Auto-generated method stub
		return userDao.quseryByTypeAdmin();
	}

	@Override
	public int quseryByTypeHuiYuan() {
		// TODO Auto-generated method stub
		return userDao.quseryByTypeHuiYuan();
	}



	
}
