package com.naythan.QuizApp.serivces;

import com.naythan.QuizApp.Entity.*;
import com.naythan.QuizApp.dao.QuestionDao;
import com.naythan.QuizApp.dao.QuizDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

   private final QuizDao quizDao;
   private final QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String title,String category,Integer numQn){
            try {
                if (title == null || title.isEmpty()) {
                    return new ResponseEntity<>("Quiz title is required", HttpStatus.BAD_REQUEST);
                }
                if (category == null || category.isEmpty()) {
                    return new ResponseEntity<>("Category is required", HttpStatus.BAD_REQUEST);
                }
                if (numQn == null || numQn <= 0) {
                    return new ResponseEntity<>("Number of questions must be positive", HttpStatus.BAD_REQUEST);
                }

                Optional<List<Question>> randomQuestions = questionDao.getRandomQuestions(category, numQn);

                if (randomQuestions.isEmpty() || randomQuestions.get().isEmpty()) {
                    return new ResponseEntity<>("Not enough questions in the given category", HttpStatus.NOT_FOUND);
                }

                Quiz quiz = new Quiz();
                quiz.setTitle(title);
                quiz.setCategory(category);
                quiz.setQuestions(randomQuestions.get());

                quizDao.save(quiz);
                return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("Failed to create Quiz ",HttpStatus.INTERNAL_SERVER_ERROR);
            }


    }

    public ResponseEntity<ApiResponce<List<QuestionWrapper>>> getQuiz(Integer id){

        try {
            Optional<Quiz> quiz=  quizDao.findById(id);
            if (quiz.isEmpty()){
                return new ResponseEntity<>(new ApiResponce<>("Quiz not found",null),HttpStatus.NOT_FOUND);
            }

            List<Question> qn_db= quiz.get().getQuestions();
            List<QuestionWrapper> qn_user= new ArrayList<>();

            for (Question q :qn_db){
                QuestionWrapper qnWrapper = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption_a(),q.getOption_b(),q.getOption_c(),q.getOption_d());

                qn_user.add(qnWrapper);
            }

            return  new ResponseEntity<>(new ApiResponce<>("Quiz",qn_user),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponce<>("Failed to get quiz",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<String> getResult(Integer id, List<Response> responce){

        try {
            Optional<Quiz> quiz= quizDao.findById(id);
            if (quiz.isEmpty()){
                return new ResponseEntity<>("quiz not found",HttpStatus.NOT_FOUND);
            }

            List<Question> questions= quiz.get().getQuestions();
            int i=0;
            int result=0;

            for (Response r:responce){

                if (r.getResponse().equals(questions.get(i).getCorrect_Option())){
                    result++;
                }
                i++;
            }
            return new ResponseEntity<>("The result is "+result+"/"+questions.stream().count(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Quiz",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
