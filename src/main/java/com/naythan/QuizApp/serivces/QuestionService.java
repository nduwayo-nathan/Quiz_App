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
    public String createQuestion(Question question){

        questionDao.save(question);
        Integer id= question.getId();
        return "successfully created:\n"+questionDao.findById(id);

    }

    public  String deleteQuestion(Integer id){
        questionDao.deleteById(id);
        return "deleted";
    }
    public String updateQuestion(Integer id ,Question question){
        Optional<Question> qnOption = questionDao.findById(id);

        Question oldQn = qnOption.get();
       oldQn.setQuestion(question.getQuestion());
       oldQn.setOption_a(question.getOption_a());
       oldQn.setOption_b(question.getOption_b());
       oldQn.setOption_c(question.getOption_d());
       oldQn.setCorrect_Option(question.getCorrect_Option());
       oldQn.setCategory(question.getCategory());

      questionDao.save(oldQn);

      return "Updated";
    }
}
