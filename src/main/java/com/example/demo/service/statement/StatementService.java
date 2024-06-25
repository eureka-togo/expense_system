package com.example.demo.service.statement;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Statement;

public interface StatementService {
	Iterable<Statement> selectAll();
	Optional<Statement> selectOneById (Integer statementid);
	void insertStatement(Statement statement);
	void updateStatement(Statement statement);
	void deleteStatementById(Integer statementid);
	List<Statement> findByUserid(Integer userid);

}
