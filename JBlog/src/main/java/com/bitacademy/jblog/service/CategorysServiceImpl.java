package com.bitacademy.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.CategorysDao;
import com.bitacademy.jblog.repository.CategorysDaoImpl;
import com.bitacademy.jblog.repository.CategorysVo;

@Service
public class CategorysServiceImpl implements CategorysService {
	@Autowired
	CategorysDao categoryDaoImpl;
	
	@Override
	public List<CategorysVo> getList(Long userNo) {
		List<CategorysVo> list = categoryDaoImpl.selectById(userNo);
		return list;
	}

	@Override
	public boolean write(CategorysVo vo) {
		int insertedCount = categoryDaoImpl.insert(vo);
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(Long userNo) {
		int deletedCount = categoryDaoImpl.delete(userNo);
		return 1 == deletedCount;
	}

}
