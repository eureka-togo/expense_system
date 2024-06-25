package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "statement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statementid;
    
    private Integer userid;
    private LocalDate statementCreationDate;
    private Integer subjectid;
    private String content;
    private String note;
    private Integer statementPrice;
    private Integer expenseid;

    // Getter and Setter for statementCreationDate
    public LocalDate getStatementCreationDate() {
        return statementCreationDate;
    }

    public void setStatementCreationDate(LocalDate statementCreationDate) {
        this.statementCreationDate = statementCreationDate;
    }
}
