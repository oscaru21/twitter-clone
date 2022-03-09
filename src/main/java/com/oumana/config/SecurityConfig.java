package com.oumana.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

import com.oumana.security.CustomUserDetailService;


@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
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
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
			
			http.headers().frameOptions().disable();
			
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}
//	//In memory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//		UserDetails oscar = User.builder().username("oscar").password(passwordEncoder().encode("passwordd"))
//				.roles("USER").build();
//		return new InMemoryUserDetailsManager(oscar, admin);
//	}
}
