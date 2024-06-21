package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Form
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
	private Integer userid;
	@NotBlank(message = "だめだよ")
	private String name;
	private Integer employeeNumber;
	private Integer departmentid;
	//@NotBlank
	private String roles;
	//@NotBlank
	//@Email
	private String email;
	//@NotBlank
	private String password;

}
