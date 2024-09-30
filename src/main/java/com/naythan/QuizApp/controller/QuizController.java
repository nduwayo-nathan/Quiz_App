package com.naythan.QuizApp.controller;

import com.naythan.QuizApp.Entity.ApiResponce;
import com.naythan.QuizApp.Entity.QuestionWrapper;
import com.naythan.QuizApp.Entity.Quiz;
import com.naythan.QuizApp.Entity.Response;
import com.naythan.QuizApp.serivces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private  final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam Integer numQn){
        return quizService.createQuiz(title,category,numQn);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<List<QuestionWrapper>>> getQuiz(@PathVariable Integer id){
      return   quizService.getQuiz(id);
    }
    @GetMapping("/result/{id}")
    public ResponseEntity<String> getResult(@PathVariable Integer id,@RequestBody List<Response> response){
        return quizService.getResult(id,response);
    }
}
