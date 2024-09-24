package com.naythan.QuizApp.controller;


import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.serivces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable Integer id){
       return questionService.getQuestionById(id);
    }
    @PostMapping("/add")
    public String createQuestion(@RequestBody Question question){
       return questionService.createQuestion(question);
    }
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Integer id){
       return questionService.deleteQuestion(id);
    }
    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable Integer id ,@RequestBody Question question){
        return  questionService.updateQuestion(id,question);
    }

}
