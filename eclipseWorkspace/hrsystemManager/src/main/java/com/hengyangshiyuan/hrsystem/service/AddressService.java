package com.hengyangshiyuan.hrsystem.service;

import java.util.List;

import com.hengyangshiyuan.hrsystem.bean.Address;

public interface AddressService {

	Address queryAddress(int userId);

	void updateAdress(Address address);

	void addAdress(Address address);

	List<Address> queryAddressList(int id);

	void updateType(String type, int id);

	void updateTypeByupdateType(String updateType,int userId);

	void deleteAddress(String type, int id);

}
