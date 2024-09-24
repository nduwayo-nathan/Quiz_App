package com.naythan.QuizApp.serivces;

import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

 private final QuestionDao questionDao;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }
}
