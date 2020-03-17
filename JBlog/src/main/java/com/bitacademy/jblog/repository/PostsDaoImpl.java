package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostsDaoImpl implements PostsDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(PostsVo vo) {
		int insertedCount = sqlSession.insert("post.insert", vo);
		return insertedCount;
	}

	@Override
	public List select(Long userNo) {
		List<PostsVo> list = sqlSession.selectList("post.select", userNo);
		return list;
	}

	@Override
	public PostsVo selectfirst(Long userNo) {
		PostsVo vo = sqlSession.selectOne("post.selectFirst", userNo);
		return vo;
	}
}
