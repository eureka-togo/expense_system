package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Statement;
import com.example.demo.form.StatementForm;
import com.example.demo.service.statement.StatementService;

@Controller
@RequestMapping("/statementList")
public class StatementController {
	@Autowired
	StatementService service;
	
	@ModelAttribute
	public StatementForm setUpForm() {
		StatementForm form = new StatementForm();
		return form;
	}
	
	@GetMapping
	public String showList(StatementForm statementForm, Model model) {
		statementForm.setNewStatement(true);
		Iterable<Statement> list = service.selectAll();
		model.addAttribute("list", list);
		model.addAttribute("title", "やぁ");
		return "statement/statementList";
	}
    

}
