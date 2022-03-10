package com.oumana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oumana.entity.User;
import com.oumana.exceptions.ResourceNotFoundException;
import com.oumana.payload.UserResponse;
import com.oumana.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("user", "username", username));
	}

	@Override
	public UserResponse getUsers(Integer pageNo, Integer pageSize) {
		//create Pageable interface
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> posts = userRepo.findAll(pageable);
		//map user response
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(posts.getContent());
		userResponse.setPageNo(posts.getNumber());
		userResponse.setPageSize(posts.getSize());
		userResponse.setTotalElements(posts.getTotalElements());
		userResponse.setTotalPages(posts.getTotalPages());
		userResponse.setLast(posts.isLast());
		
		return userResponse;
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id.toString()));
		userRepo.delete(user);
	}

}
