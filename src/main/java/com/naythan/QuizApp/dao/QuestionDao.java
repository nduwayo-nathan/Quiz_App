package com.naythan.QuizApp.dao;

import com.naythan.QuizApp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao  extends JpaRepository<Question,Integer> {

    @Query(value = "SELECT * FROM questions q WHERE q.category=:category LIMIT :numQn",nativeQuery = true)
     Optional<List<Question>> getRandomQuestions(String category,Integer numQn);

}
