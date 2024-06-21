package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.SignupForm;
import com.example.demo.service.signupservice.SignupService;

import jakarta.servlet.http.HttpSession;

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
                         Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // FormからEntity詰め替え
        User user = new User();
        user.setName(signupForm.getName());
        user.setEmployeeNumber(signupForm.getEmployeeNumber());
        user.setDepartmentid(signupForm.getDepartmentid());
        user.setRoles(signupForm.getRoles());
        user.setEmail(signupForm.getEmail());
        user.setPassword(signupForm.getPassword());

        // セッションにユーザー情報を保存
        session.setAttribute("signupUser", user);

        return "redirect:/signup/signupConfirm";
    }

    @GetMapping("/signupConfirm")
    public String signupConfirm(Model model, HttpSession session) {
        // セッションからユーザー情報を取得
        User user = (User) session.getAttribute("signupUser");
        if (user == null) {
            return "redirect:/signup";
        }
        model.addAttribute("signupUser", user);
        return "signupConfirm";
    }

    @PostMapping("/confirm")
    public String confirm(HttpSession session, RedirectAttributes redirectAttributes) {
        // セッションからユーザー情報を取得
        User user = (User) session.getAttribute("signupUser");
        if (user == null) {
            return "redirect:/signup";
        }

        // ユーザー情報をDBに登録
        service.insertUser(user);

        // セッションからユーザー情報を削除
        session.removeAttribute("signupUser");
        
        return "redirect:/signup/signupComplete";
    }

    @GetMapping("/signupComplete")
    public String signupComplete() {
        return "signupComplete";
    }
}
