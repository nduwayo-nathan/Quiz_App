package com.naythan.QuizApp.controller;

import com.naythan.QuizApp.serivces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private  final QuizService quizService;
    @PostMapping("/create")
    public String createQuiz(@RequestParam String title,@RequestParam String category,@RequestParam Integer numQn){
        return quizService.createQuiz(title,category,numQn);
    }
}
