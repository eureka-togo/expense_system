package com.example.demo.service.signupservice;

import com.example.demo.entity.User;

public interface SignupService {
	Iterable<User> selectAll();
	void insertUser(User user);
	void deleteUserById(Integer userid);

}
