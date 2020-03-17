package com.bitacademy.jblog.repository;

import java.util.List;

public interface PostsDao {
	public int insert(PostsVo vo);
	public List<PostsVo> select(Long userNo);
	public PostsVo selectfirst(Long userNo);
}
