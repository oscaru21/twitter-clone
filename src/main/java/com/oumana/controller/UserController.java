package com.oumana.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oumana.entity.User;
import com.oumana.payload.UserResponse;
import com.oumana.service.UserServiceImpl;
import com.oumana.util.AppConstants;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping
	public ResponseEntity<UserResponse> getUsers(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortingBy", defaultValue = AppConstants.DEFAULT_SORTING_FIELD, required = false) String sortingBy
			) {
		return ResponseEntity.ok().body(userService.getUsers(pageNo, pageSize));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok().body(userService.getUserByUsername(username));
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
	
		URI uRI = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.created(uRI).body(userService.createUser(user));
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user){
		return ResponseEntity.ok().body(userService.updateUser(user));
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
}
