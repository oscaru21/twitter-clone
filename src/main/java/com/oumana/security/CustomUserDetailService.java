package com.oumana.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oumana.entity.Role;
import com.oumana.entity.User;
import com.oumana.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	UserRepo userRepo;

	// this method retrieves a user from the database and maps it to a UserDetails
	// class
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// search for a user in the database
		User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not find with username or email: " + usernameOrEmail));
		// map User to UserDetails
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	// map the authorities that should be granted to the caller if they
	// presented the correct username and password and the user is enabled.
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}
}
