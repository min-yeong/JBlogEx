package com.bitacademy.jblog.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BlogsDao")
public class BlogsDaoImpl implements BlogsDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public BlogsVo selectTitle(String id) {
		BlogsVo vo = null;
		try {
			vo = sqlSession.selectOne("blogs.selectByNo", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int insert(Long userNo) {
		int insertedCount = 0;
		try {
			insertedCount = sqlSession.insert("blogs.insert", userNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return insertedCount;
	}

	@Override
	public int update(Long userNo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("blogs.update", userNo);
		return updatedCount;
	}
}
