package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.LoginUserDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class LoginUserDetailService implements UserDetailsService{
	private final UserRepository userRepository;

	  public LoginUserDetailService(UserRepository userRepository) {
	    this.userRepository = userRepository;
	  }

	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Optional<User> _user = userRepository.findByEmail(email);
	    return _user.map(user -> new LoginUserDetails(user))
	        .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
	  }

}