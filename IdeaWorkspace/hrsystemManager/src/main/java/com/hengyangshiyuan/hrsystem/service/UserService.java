package com.hengyangshiyuan.hrsystem.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Page;
import com.hengyangshiyuan.hrsystem.bean.User;



public interface UserService {
	

	/**
	 * 登录业务
	 * 
	 * @param user
	 *            需要登录的用户
	 * @return 返回登录后的用户信息<br/>如果返回user对象有值。说明用户名和密码正确可以登录<br/>
	 * 	如果返回的是null,用户名和密码错误。
	 */
	public User login(User user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            需要注册到数据库的用户信息
	 */
	public void regist(User user);

	/**
	 * 检查 用户名是否存在
	 * @param username	需要检查的用户名
	 * @return	如果返回true,说明用户名已存在<br/>
	 * 			如果返回false，说明用户名可用。
	 */
	public boolean existUsername(String username);


	public User queryUserByUsernameAndPassword(String username, String password);
	
	public User queryUserIdByUsername(String username);

	public String queryLastDate(int id);

	public void UpdateLastDate(String date,int id);

	public void updateUser(int id,Date brithday, String sex, String email, String phone);

	public void updePassword(int id, String newpasswod);

	public int queryForPageTotalCount();

	public void managerUpdateUser(User paramUser);

	public void deleteUser(int paramId);

	public void updateGongGao(String gonggao,String usertype);

	public int quseryByTypeAdmin();

	public int quseryByTypeHuiYuan();


	
}
