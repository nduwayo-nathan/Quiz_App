package com.naythan.QuizApp.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    @Id
    public Integer id;
    private String question;
    private  String option_a;
    private  String option_b;
    private  String option_c;
    private  String option_d;
}
