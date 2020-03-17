package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategorysDaoImpl implements CategorysDao {
	@Autowired
	SqlSession sqlSession;
	@Override
	public List<CategorysVo> selectById(Long userNo) {
		List<CategorysVo> list = sqlSession.selectList("category.selectById", userNo);
		return list;
	}

	@Override
	public int insert(CategorysVo vo) {
		System.out.println("vo:"+vo);
		int insertedCount = sqlSession.insert("category.insert", vo);
		return insertedCount;
	}

	@Override
	public int delete(Long userNo) {
		int deletedCount = sqlSession.delete("category.delete", userNo);
		return deletedCount;
	}

}
