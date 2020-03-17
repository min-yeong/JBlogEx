package com.bitacademy.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.exception.UsersDaoException;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(UsersVo vo) {
		int insertedCount = 0;
		try {
			insertedCount = sqlSession.insert("users.insert", vo);
		} catch(Exception e) {
			// 명시적인 Exception으로 변환
			e.printStackTrace();
			throw new UsersDaoException("가입 중 오류 발생", vo);
		}
		return insertedCount;
	}

	@Override
	public UsersVo selectUser(String id) {
		UsersVo vo = null;
		try {
			vo = sqlSession.selectOne("users.selectById", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public UsersVo selectUser(String id, String password) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("password", password);
		
		UsersVo users = sqlSession.selectOne("users.selectByIdAndPassword", paramMap);
		// 주의 : selectOne 메서드는 단일 레코드가 넘어올 때만 사용할 수 있다.
		return users;
	}
}
