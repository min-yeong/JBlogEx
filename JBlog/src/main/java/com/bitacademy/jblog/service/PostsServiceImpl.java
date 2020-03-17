package com.bitacademy.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.PostsDao;
import com.bitacademy.jblog.repository.PostsVo;

@Service
public class PostsServiceImpl implements PostsService {
	@Autowired
	PostsDao postDaoImpl;
	
	@Override
	public boolean postwrite(PostsVo vo) {
		int insertedCount = postDaoImpl.insert(vo);
		return 1 == insertedCount;
	}

	@Override
	public List<PostsVo> getPost(Long userNo) {
		List<PostsVo> list = postDaoImpl.select(userNo);
		return list;
	}

	@Override
	public PostsVo firstPost(Long userNo) {
		PostsVo vo = postDaoImpl.selectfirst(userNo);
		return vo;
	}

}
