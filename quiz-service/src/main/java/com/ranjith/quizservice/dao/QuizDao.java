package com.ranjith.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranjith.quizservice.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
