package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="statement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statementid;
	
	private Integer userid;
	private Integer statementCreationDate;
	private Integer subjectid;
	private String content;
	private Integer statementPrice;
	private String note;
	private Integer expenseid;
}
