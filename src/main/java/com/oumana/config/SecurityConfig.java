package com.oumana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class SecurityConfig{
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
			
			http.headers().frameOptions().disable();
			
		}
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
				.build();
		UserDetails oscar = User.builder().username("oscar").password(passwordEncoder().encode("passwordd"))
				.roles("USER").build();
		return new InMemoryUserDetailsManager(oscar, admin);
	}
}
