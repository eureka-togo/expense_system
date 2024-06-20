package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.SignupForm;
import com.example.demo.service.signupservice.SignupService;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	SignupService service;
	
	@ModelAttribute
	public SignupForm setUpForm() {
		SignupForm form = new SignupForm();
		return form;
	}
	@PostMapping("/insert")
	public String insert(@Validated SignupForm signupForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		//FormからEntity詰め替え
		User user = new User();
		user.setName(signupForm.getName());
		user.setEmployeeNumber(signupForm.getEmployeeNumber());
		user.setDepartmentid(signupForm.getDepartmentid());
		user.setRoles(signupForm.getRoles());
		user.setEmail(signupForm.getEmail());
		user.setPassword(signupForm.getPassword());
		
		if(!bindingResult.hasErrors()) {
			service.insertUser(user);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/index.html";
		} else {
			return "/insert";
		}		
	}
	
	
	
	

}
