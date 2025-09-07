package com.ssafy.trip.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.user.model.User;
import com.ssafy.trip.user.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void joinUser(User user) throws Exception {
		userDao.joinUser(user);
	}

	@Override
	public User loginUser(Map<String, String> map) throws Exception {
		return userDao.loginUser(map);
	}

	@Override
	public User findByUserEmail(String userEmail) throws Exception {
		return userDao.findByUserEmail(userEmail);
	}

	@Override
	public User findByUserName(String userName) throws Exception{
		return userDao.findByUserName(userName);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userDao.deleteUser(id);
	}
	
	@Override
	public User userInfo(String userEmail) throws Exception {
		return userDao.userInfo(userEmail);
	}
	
	@Override
	public void saveRefreshToken(String userEmail, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userEmail", userEmail);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userEmail) throws Exception {
		return userDao.getRefreshToken(userEmail);
	}

	@Override
	public void deleRefreshToken(String userEmail) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userEmail", userEmail);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}
}
