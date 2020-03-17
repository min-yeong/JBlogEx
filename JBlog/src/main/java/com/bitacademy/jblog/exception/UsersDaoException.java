package com.bitacademy.jblog.exception;

import com.bitacademy.jblog.repository.UsersVo;

public class UsersDaoException extends RuntimeException {
	private UsersVo vo = null;
	
	public UsersDaoException() {
		super();
	}
	
	public UsersDaoException(String message) {
		super(message);
	}
	public UsersDaoException(String message, UsersVo vo) {
		super(message);
		this.vo = vo;
	}
	public UsersVo getVo() {
		return vo;
	}
	public void setVo(UsersVo vo) {
		this.vo = vo;
	}
	@Override
	public String toString() {
		return "UsersDaoException [vo=" + vo + "]";
	}
}
