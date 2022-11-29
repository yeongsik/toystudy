package com.toystudy.quiz.controller;

import com.toystudy.quiz.request.QuizRequest;
import com.toystudy.quiz.response.QuizResponse;
import com.toystudy.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;

    // 퀴즈 저장
    @PostMapping("/quizzes")
    public void saveQuiz(@RequestBody @Valid QuizRequest request) {
        quizService.saveQuiz(request);
    }

    // 퀴즈 단건 조회
    @GetMapping("/quizzes/{quizId}")
    public QuizResponse getQuiz(@PathVariable Long quizId) {
        return quizService.getQuiz(quizId);
    }

    // 퀴즈 다건 조회
    @GetMapping("/quizzes")
    public List<QuizResponse> getQuizList(Pageable page) {
        return quizService.getQuizList(page);
    }
}
