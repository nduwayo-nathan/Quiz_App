package com.naythan.QuizApp.dao;

import com.naythan.QuizApp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao  extends JpaRepository<Question,Integer> {

}
