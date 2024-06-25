package com.example.demo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementForm {
    private Integer statementid;
    private Integer userid;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate statementCreationDate;
    
    private Integer subjectid;
    private String content;
    private String note;
    private Integer statementPrice;
    private Integer expenseid;
    private boolean newStatement;
}
