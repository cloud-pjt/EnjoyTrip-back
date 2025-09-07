package com.ssafy.trip.user.model.service;

import java.util.Map;

import com.ssafy.trip.user.model.User;

public interface UserService {
	void joinUser(User user) throws Exception;
	User loginUser(Map<String, String> map) throws Exception;
	User findByUserEmail(String userEmail) throws Exception;
	User findByUserName(String userName) throws Exception;
	void updateUser(User user) throws Exception;
	void deleteUser(String id) throws Exception;
	void saveRefreshToken(String userEmail, String refreshToken) throws Exception;
	Object getRefreshToken(String userEmail) throws Exception;
	void deleRefreshToken(String userEmail) throws Exception;
	User userInfo(String userEmail) throws Exception;
}
