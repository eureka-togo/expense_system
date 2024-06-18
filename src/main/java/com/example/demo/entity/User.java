package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private Integer userid;
	private String name;
	private Integer employeeNumber;
	private Integer departmentid;
	private Integer roleid;
	private String email;
	private String password;

}
