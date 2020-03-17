package com.bitacademy.jblog.repository;

public interface BlogsDao {
	public BlogsVo selectTitle(String id);
	public int insert(Long userNo);
	public int update(Long userNo);
}
