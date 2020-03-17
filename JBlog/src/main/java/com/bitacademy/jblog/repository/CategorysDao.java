package com.bitacademy.jblog.repository;

import java.util.List;

public interface CategorysDao {
	public List<CategorysVo> selectById(Long userNo);
	public int insert(CategorysVo vo);
	public int delete(Long userNo);
}

