package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseSystemApplication.class, args);
		/*.getBean(ExpenseSystemApplication.class).execute();
	}
	@Autowired
	StatementService service;
	private void execute() {
		showList();
	}
	
	private void showList() {
		Iterable<Statement> statementes= service.selectAll();
		for(Statement statement : statementes) {
		System.out.println(statement);
	}
		System.out.println("できたよ");*/

}
}

