package com.bitacademy.jblog.service;

import java.util.List;

import com.bitacademy.jblog.repository.CategorysVo;

public interface CategorysService {
	public boolean write(CategorysVo vo);
	public List<CategorysVo> getList(Long userNo);
	public boolean delete(Long userNo);
}
