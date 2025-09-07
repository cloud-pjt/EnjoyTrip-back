package com.ssafy.trip.user.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.user.model.User;

@Mapper
public interface UserDao {
	void joinUser(User user) throws SQLException;
	User loginUser(Map<String, String> map) throws SQLException;
	User findByUserEmail(String email) throws SQLException;
	User findByUserName(String name) throws SQLException;
	void updateUser(User user) throws SQLException;
	void deleteUser(String email) throws SQLException;
	User userInfo(String email) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String email) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
