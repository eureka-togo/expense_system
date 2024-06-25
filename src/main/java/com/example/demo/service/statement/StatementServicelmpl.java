package com.example.demo.service.statement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Statement;
import com.example.demo.repository.StatementRepository;

@Service
@Transactional
public class StatementServicelmpl implements StatementService {

	@Autowired
	StatementRepository repository;

	@Override
	public Iterable<Statement> selectAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Statement> selectOneById(Integer statementid) {
		return repository.findById(statementid);
	}

	@Override
	public void insertStatement(Statement statement) {
		repository.save(statement);

	}

	@Override
	public void updateStatement(Statement statement) {
		repository.save(statement);

	}

	@Override
	public void deleteStatementById(Integer statementid) {
		repository.deleteById(statementid);

	
}

	@Override
	public List<Statement> findByUserid(Integer userid) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByUserid(userid);
	}
}
