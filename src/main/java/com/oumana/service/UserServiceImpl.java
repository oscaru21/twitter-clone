package com.oumana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oumana.entity.User;
import com.oumana.exceptions.ResourceNotFoundException;
import com.oumana.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User getUserByUsername(String username) {
		return userRepo
				.findUserByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("user", "username", username));
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

}
