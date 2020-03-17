package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.BlogsDao;
import com.bitacademy.jblog.repository.BlogsVo;

@Service("blogsService")
public class BlogsServiceImpl implements BlogsService {
	@Autowired
	BlogsDao blogDao;

	@Override
	public BlogsVo getBlog(String id) {
		BlogsVo vo = blogDao.selectTitle(id);
		return vo;
	}

	@Override
	public boolean join(Long userNo) {
		System.out.println("JOIN userNO:" + userNo);
		int insertedCount = blogDao.insert(userNo);
		return 1 == insertedCount;
	}

	@Override
	public boolean modify(Long userNo) {
		int updatedCount = blogDao.update(userNo);
		return 1 == updatedCount;
	}

}
