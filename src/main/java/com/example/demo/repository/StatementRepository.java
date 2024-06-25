package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Statement;

public interface StatementRepository extends CrudRepository<Statement, Integer> {
	List<Statement> findByUserid(Integer userid);

}