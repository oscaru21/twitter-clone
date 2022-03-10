package com.oumana.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.Role;
import com.oumana.entity.User;
import com.oumana.payload.LoginDto;
import com.oumana.payload.SignUpDto;
import com.oumana.repository.RoleRepo;
import com.oumana.repository.UserRepo;
import com.oumana.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//get a token from the provider
		String token = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("User logged successfully");
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto){
		//check if username already exists
		if(userRepo.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
		}
		//check if email already exists
		if(userRepo.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
		}
		//Creates user and assign roles
		User user = new User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		
		Role roles = roleRepo.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(roles));
		
		//save user to db
		userRepo.save(user);
		
		
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}
}
