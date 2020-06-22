package com.hengyangshiyuan.hrsystem.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.User;

@Mapper
public interface UserDao {

	/**
	 * 根据用户名查询用户信息。
	 * @param username	用户名密码
	 * @return	返回查询到的用户<br/>如果有值，说明用户名已存在，如果返回null,说明用户名可用。
	 */
	public User queryUserByUsername(@Param(value="username")String username);

	/**
	 * 根据用户名和密码查询 用户信息。
	 * @param username	用户名
	 * @param password	密码
	 * @return 返回查询到的用户<br/>如果有值，说明用户名和密码正确，可以登录。如果返回null，说明用户名或密码错误。
	 */
	public User queryUserByUsernameAndPassword(@Param(value="username")String username, @Param(value="password")String password);

	/**
	 * 保存用户信息到数据库（用户注册）
	 * @param user	注册的用户信息
	 * @return	insert语句影响的行数
	 */
	public int saveUser(User user);

	public String queryLastDate(int id);

	public void UpdateLastDate(@Param(value="date")String date,@Param(value="id")int id);

	public void updateUser(@Param(value="id")int id,@Param(value="brithday")Date brithday, @Param(value="sex")String sex,  @Param(value="email")String email, @Param(value="phone")String phone);

	public void updePassword(@Param(value="id")int id, @Param(value="newpasswod")String newpasswod);

	public int queryForPageTotalCount();

	public void managerUpdateUser(User paramUser);

	public void deleteUser(int paramId);

	public void updateGongGao(@Param(value="gonggao")String gonggao,@Param(value="usertype") String usertype);

	public int quseryByTypeAdmin();

	public int quseryByTypeHuiYuan();


	
	
}
