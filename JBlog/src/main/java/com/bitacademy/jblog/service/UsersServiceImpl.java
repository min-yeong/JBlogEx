package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.UsersDao;
import com.bitacademy.jblog.repository.UsersVo;

@Service("UsersService")
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersDao userDao;
		
	@Override
	public boolean join(UsersVo vo) {
		int insertedCount = userDao.insert(vo);
		return 1 == insertedCount;
	}

	@Override
	public UsersVo getUser(String id) {
		UsersVo vo = userDao.selectUser(id);
		return vo;
	}

	@Override
	public UsersVo getUser(String id, String password) {
		UsersVo vo = userDao.selectUser(id, password);
		return vo;
	}
}
