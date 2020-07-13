package com.hengyangshiyuan.hrsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengyangshiyuan.hrsystem.bean.Address;

@Mapper
public interface AddressDao {

	Address queryAddress(int userId);

	void updateAdress(Address address);

	void addAdress(Address address);

	List<Address> queryAddressList(int id);

	void updateType(@Param(value="type")String type,@Param(value="id")int id);
 
	void updateTypeByupdateType(@Param(value="type")String updateType, @Param(value="userId")int userId);

	void deleteAddress(@Param(value="type")String type,@Param(value="id")int id);
	
}
