package com.bitacademy.jblog.service;

import com.bitacademy.jblog.repository.BlogsVo;

public interface BlogsService {
	public BlogsVo getBlog(String id);
	public boolean join(Long userNo);
	public boolean modify(Long userNo);
}
