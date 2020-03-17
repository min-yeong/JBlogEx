package com.bitacademy.jblog.service;

import java.util.List;

import com.bitacademy.jblog.repository.PostsVo;

public interface PostsService {
	public boolean postwrite(PostsVo vo);
	public List<PostsVo> getPost(Long userNo);
	public PostsVo firstPost(Long userNo);
}
