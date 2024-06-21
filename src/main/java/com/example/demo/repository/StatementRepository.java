package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Statement;

public interface StatementRepository extends CrudRepository<Statement, Integer> {

}
