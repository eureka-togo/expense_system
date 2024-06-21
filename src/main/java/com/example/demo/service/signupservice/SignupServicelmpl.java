package com.example.demo.service.signupservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class SignupServicelmpl implements SignupService {
	//Repositoryの注入
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public Iterable<User> selectAll() {
		return repository.findAll();
	}

	@Override
	public void insertUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repository.save(user);

	}

	@Override
	public void deleteUserById(Integer userid) {
		repository.deleteById(userid);

	}

}
