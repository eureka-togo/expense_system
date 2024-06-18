package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class ExpenseSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseSystemApplication.class, args)
		.getBean(ExpenseSystemApplication.class).execute();
	}
	@Autowired
	UserRepository repository;
	private void execute() {
		setup();
	}
	
	private void setup() {
		User user2 = new User(null,"渡辺 良太",999,2,2,"watanape@gmail.com","aaa");
		user2 = repository.save(user2);
		System.out.println("できたよ");
	}

}
