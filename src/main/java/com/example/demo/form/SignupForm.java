package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Form
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
	//private Integer userid;
	private String name;
	private Integer employeeNumber;
	private Integer departmentid;
	private String roles;
	private String email;
	private String password;

}
