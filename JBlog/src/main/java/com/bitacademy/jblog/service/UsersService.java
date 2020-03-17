package com.bitacademy.jblog.service;

import com.bitacademy.jblog.repository.UsersVo;

public interface UsersService {
	public boolean join(UsersVo vo);
	public UsersVo getUser(String id);
	public UsersVo getUser(String id, String password);
}
