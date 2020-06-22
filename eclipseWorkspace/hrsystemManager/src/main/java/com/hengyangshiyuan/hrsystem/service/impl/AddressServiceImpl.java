package com.hengyangshiyuan.hrsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengyangshiyuan.hrsystem.bean.Address;
import com.hengyangshiyuan.hrsystem.dao.AddressDao;
import com.hengyangshiyuan.hrsystem.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	 private AddressDao addressDao;

	@Override
	public Address queryAddress(int userId) {
		// TODO Auto-generated method stub
		return addressDao.queryAddress(userId);
	}

	@Override
	public void updateAdress(Address address) {
		// TODO Auto-generated method stub
		addressDao.updateAdress(address);
	}

	@Override
	public void addAdress(Address address) {
		// TODO Auto-generated method stub
		addressDao.addAdress(address);
	}

	@Override
	public List<Address> queryAddressList(int id) {
		// TODO Auto-generated method stub
		return addressDao. queryAddressList(id);
	}

	@Override
	public void updateType(String type, int id) {
		// TODO Auto-generated method stub
		addressDao.updateType(type,id);
	}

	@Override
	public void updateTypeByupdateType(String updateType,int userId) {
		// TODO Auto-generated method stub
		addressDao.updateTypeByupdateType(updateType,userId);
	}

	@Override
	public void deleteAddress(String type, int id) {
		// TODO Auto-generated method stub
		addressDao.deleteAddress(type,id);
	}

}
