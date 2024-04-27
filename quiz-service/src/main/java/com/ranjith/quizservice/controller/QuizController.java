package com.ranjith.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranjith.quizservice.model.QuestionWrapper;
import com.ranjith.quizservice.model.Quiz;
import com.ranjith.quizservice.model.QuizDto;
import com.ranjith.quizservice.model.Response;
import com.ranjith.quizservice.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
	  return quizService.createQuiz(quizDto.getCategory(),quizDto.getNoOfQuestions(),quizDto.getTitle());
	}
	
	@GetMapping("allQuizzes")
	public ResponseEntity<List<Quiz>> getAllQuizzes()
	{
		return quizService.getAllQuizzes();
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
	{
		return quizService.calculateResult(id,responses);
	}

}
