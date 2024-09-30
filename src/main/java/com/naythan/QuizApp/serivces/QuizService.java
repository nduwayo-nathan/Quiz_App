package com.naythan.QuizApp.serivces;

import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.Entity.QuestionWrapper;
import com.naythan.QuizApp.Entity.Quiz;
import com.naythan.QuizApp.dao.QuestionDao;
import com.naythan.QuizApp.dao.QuizDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

   private final QuizDao quizDao;
   private final QuestionDao questionDao;

    public String createQuiz(String title,String category,Integer numQn){
       Optional<List<Question>> randomQuestions= questionDao.getRandomQuestions(category,numQn);


        Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setCategory(category);
            quiz.setQuestions(randomQuestions.get());

            quizDao.save(quiz);
        return "created";
    }

    public List<QuestionWrapper> getQuiz(Integer id){
     Optional<Quiz> quiz=  quizDao.findById(id);

     List<Question> qn_db= quiz.get().getQuestions();
     List<QuestionWrapper> qn_user= new ArrayList<>();

    for (Question q :qn_db){
        QuestionWrapper qnWrapper = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption_a(),q.getOption_b(),q.getOption_c(),q.getOption_d());

        qn_user.add(qnWrapper);
    }


    return  qn_user;
    }

}
