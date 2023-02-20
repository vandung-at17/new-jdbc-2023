package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDao userDao;
	
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		// TODO Auto-generated method stub
		return userDao.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
