package com.naythan.QuizApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String question;
    private  String option_a;
    private  String option_b;
    private  String option_c;
    private  String option_d;
    private String category;
    private Character correct_Option;
}
