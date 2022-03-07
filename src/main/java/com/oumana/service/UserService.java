package com.oumana.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oumana.entity.User;

public interface UserService {
	User getUserByUsername(String username);
	List<User> getUsers();
	User createUser(User user);
}
