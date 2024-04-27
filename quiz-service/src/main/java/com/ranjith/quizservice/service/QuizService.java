package com.ranjith.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranjith.quizservice.dao.QuizDao;
import com.ranjith.quizservice.feign.QuizInterface;
import com.ranjith.quizservice.model.QuestionWrapper;
import com.ranjith.quizservice.model.Quiz;
import com.ranjith.quizservice.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	
	 @Autowired
	 QuizInterface quizInterface;
	 

	public ResponseEntity<String> createQuiz(String category,Integer noOfQuestions, String title) {

			 List<Integer> questions=quizInterface.getQuestionsForQuiz(category,noOfQuestions).getBody();
			
			Quiz quiz=new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(questions);
			quizDao.save(quiz);
			
	return new ResponseEntity<>("Quiz Created",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

		Quiz quiz = quizDao.findById(id).get();
		
		List<Integer> questions = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionsForQuiz=quizInterface.getQuestionsFromId(questions);
		return questionsForQuiz;
	}

	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		try {
			return new ResponseEntity<>(quizDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score=quizInterface.getScore(responses);
		return score;
	}

}
