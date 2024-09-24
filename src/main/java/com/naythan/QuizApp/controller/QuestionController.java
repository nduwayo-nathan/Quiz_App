package com.naythan.QuizApp.controller;


import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.serivces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

}
