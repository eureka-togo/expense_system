package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.LoginUserDetails;
import com.example.demo.entity.Statement;
import com.example.demo.form.StatementForm;
import com.example.demo.service.statement.StatementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

	private final StatementService statementService;

    @GetMapping
    public String index(Model model) {
        // 認証されたユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof LoginUserDetails) {
            LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
            
            // ユーザー情報をモデルに追加
            model.addAttribute("userid", userDetails.getUserid());
            model.addAttribute("name", userDetails.getName());
            model.addAttribute("employeeNumber", userDetails.getEmployeeNumber());
            model.addAttribute("departmentid", userDetails.getDepartmentid());

            // ログインしているユーザーの明細を取得してモデルに追加
            Iterable<Statement> statements = statementService.findByUserid(userDetails.getUserid());
            model.addAttribute("statements", statements);
            model.addAttribute("statementForm", new StatementForm());
        } else {
            // 認証されていない場合や認証情報が不正な場合の処理
            model.addAttribute("userid", "未認証");
            model.addAttribute("name", "未認証");
            model.addAttribute("employeeNumber", "未認証");
            model.addAttribute("departmentid", "未認証");
        }

        return "index";
    }
    

    /*@GetMapping("/signup")
    public String newSignup(SignupForm signupForm) {
        return "signup/signup";
    }*/
    
    /*@GetMapping("/statementCreate")
    public String newStatementCreate(StatementForm statementForm) {
    	return "statement/statementCreate";
    }*/
    
    
}

