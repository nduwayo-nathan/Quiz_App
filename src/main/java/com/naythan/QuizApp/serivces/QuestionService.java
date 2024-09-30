package com.naythan.QuizApp.serivces;

import com.naythan.QuizApp.Entity.ApiResponce;
import com.naythan.QuizApp.Entity.Question;
import com.naythan.QuizApp.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

 private final QuestionDao questionDao;


    public ResponseEntity<ApiResponce<List<Question>>> getAllQuestions(){
        try {
           List<Question> questions = questionDao.findAll();
           if (questions.isEmpty()){
               return  new ResponseEntity<>(new ApiResponce<>("Questions not found",Collections.emptyList()),HttpStatus.OK);
           }
           return new ResponseEntity<>(new ApiResponce<>("Questions :\n",questions),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(new ApiResponce<>("Failed to get questions",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public ResponseEntity<ApiResponce<Question>> getQuestionById(Integer id){


        try {
            Optional<Question> question= questionDao.findById(id);
            return question.map(value -> new ResponseEntity<>(new ApiResponce<>("Question:\n ",value ),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponce<>("Question not found",null),HttpStatus.NOT_FOUND));

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponce<>("failed to get question",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<String> createQuestion(Question question){
        try {
            if (question==null){
                return new ResponseEntity<>("Question object is null",HttpStatus.BAD_REQUEST);
            }
            questionDao.save(question);
            return new ResponseEntity<>("Successfully created",HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create question",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public  ResponseEntity<String> deleteQuestion(Integer id){
        try {
            Optional<Question> question=questionDao.findById(id);
            if (question.isEmpty()){
                return new ResponseEntity<>("Question not Found",HttpStatus.NOT_FOUND);
            }
            questionDao.deleteById(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<String> updateQuestion(Integer id ,Question question){

        try {

            Optional<Question> qnOption = questionDao.findById(id);
            if (qnOption.isEmpty()){
                return new ResponseEntity<>("Question not found",HttpStatus.NOT_FOUND);
            }

            Question oldQn = qnOption.get();
            oldQn.setQuestion(question.getQuestion());
            oldQn.setOption_a(question.getOption_a());
            oldQn.setOption_b(question.getOption_b());
            oldQn.setOption_c(question.getOption_d());
            oldQn.setCorrect_Option(question.getCorrect_Option());
            oldQn.setCategory(question.getCategory());

            questionDao.save(oldQn);

            return new ResponseEntity<>("Question Updated",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
