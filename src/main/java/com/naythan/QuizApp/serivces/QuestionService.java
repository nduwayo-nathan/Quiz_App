package com.naythan.QuizApp.serivces;

import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

 private final QuestionDao questionDao;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public Optional<Question> getQuestionById(Integer id){
        return questionDao.findById(id);
    }
}
