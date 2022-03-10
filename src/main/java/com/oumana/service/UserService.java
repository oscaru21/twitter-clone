package com.oumana.service;

import com.oumana.entity.User;
import com.oumana.payload.UserResponse;

public interface UserService {
	User getUserByUsername(String username);
	UserResponse getUsers(Integer pageNo, Integer pageSize);
	User updateUser(User user);
	void deleteUser(Long id);
}
