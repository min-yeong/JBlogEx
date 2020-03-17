package com.bitacademy.jblog.repository;

public interface UsersDao {
	public int insert(UsersVo vo); 
	public UsersVo selectUser(String id);
	public UsersVo selectUser(String id, String password);
}
