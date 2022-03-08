package com.oumana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Boolean existByEmail(String email);
	Boolean existByUsername(String username);
}
