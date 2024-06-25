package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.config.LoginUserDetails;
import com.example.demo.entity.Statement;
import com.example.demo.form.StatementForm;
import com.example.demo.service.statement.StatementService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/statementCreate")
@RequiredArgsConstructor
public class StatementCreationController {
    
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

            // ログインしているユーザーの明細を取得してモデルに追加
            Iterable<Statement> statements = statementService.findByUserid(userDetails.getUserid());
            model.addAttribute("statements", statements);
            model.addAttribute("statementForm", new StatementForm()); // フォームを追加
        } else {
            // 認証されていない場合や認証情報が不正な場合の処理
            model.addAttribute("userid", "未認証");
            model.addAttribute("name", "未認証");
        }

        return "statement/statementCreate";
    }

    @PostMapping("/insert")
    public String createStatement(@ModelAttribute StatementForm statementForm, HttpSession session) {
        // 認証されたユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof LoginUserDetails) {
            LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
            statementForm.setUserid(userDetails.getUserid()); // ログインユーザーのIDを設定

            Statement statement = new Statement();
            statement.setUserid(statementForm.getUserid());
            statement.setStatementCreationDate(statementForm.getStatementCreationDate());
            statement.setSubjectid(statementForm.getSubjectid());
            statement.setContent(statementForm.getContent());
            statement.setStatementPrice(statementForm.getStatementPrice());
            statement.setNote(statementForm.getNote());
            statement.setExpenseid(statementForm.getExpenseid());
            
            session.setAttribute("createStatement", statement);
        }

        return "redirect:/statementCreate/statementCreateConfirm";
    }
    
    @GetMapping("/statementCreateConfirm")
    public String statementCreateConfirm(Model model, HttpSession session) {
        // セッションからユーザー情報を取得
        Statement statement = (Statement) session.getAttribute("createStatement");
        if (statement == null) {
            return "redirect:/statementCreate";
        }
        model.addAttribute("createStatement", statement);
        return "statement/statementCreateConfirm";
    }

    @PostMapping("/confirm")
    public String confirm(HttpSession session, RedirectAttributes redirectAttributes) {
        // セッションからユーザー情報を取得
        Statement statement = (Statement) session.getAttribute("createStatement");
        if (statement == null) {
            return "redirect:/statementCreate";
        }

        // ユーザー情報をDBに登録
        statementService.insertStatement(statement);

        // セッションからユーザー情報を削除
        session.removeAttribute("createStatement");
        
        return "redirect:/statementCreate/statementCreateComplete";
    }
    
    @GetMapping("/statementCreateComplete")
    public String statementCreateComplete() {
        return "statement/statementCreateComplete";
    }
}
