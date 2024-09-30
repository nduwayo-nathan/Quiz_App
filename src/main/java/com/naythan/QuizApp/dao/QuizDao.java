package com.naythan.QuizApp.dao;

import com.naythan.QuizApp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao  extends JpaRepository<Quiz,Integer> {
}
