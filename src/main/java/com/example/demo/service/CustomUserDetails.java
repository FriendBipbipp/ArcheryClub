package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class CustomUserDetails implements UserDetailsService{
	
	private UserRepository userRepository;
	public CustomUserDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("user not found with give username");
		}
		return new CustomUserDetailsImpl(user);
	}
}
