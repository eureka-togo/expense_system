package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SignupForm;

@Controller
@RequestMapping("/")
public class TestController{
	@GetMapping
    public String index () {
        return "index";
    }
	@GetMapping("/signup")
    public String newSignup(SignupForm signupForm) {
        return "signup";
    }

	/*@PostMapping("/signup")
    public String signup(SignupForm signupForm, Model model) {
        try {
            LoginUserDetailService.register(signupForm.getName(),signupForm.getEmployeeNumber(),signupForm.getDepartmentid(),signupForm.getRoles(),signupForm.getEmail(), signupForm.getPassword());
        } catch (DataAccessException e) {
            model.addAttribute("signupError", "ユーザー登録に失敗しました");
            return "signup";
        }
        return "redirect:/";
    }*/
		
	}


