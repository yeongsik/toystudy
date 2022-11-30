package com.toystudy.quiz.service;

import com.toystudy.quiz.domain.Quiz;
import com.toystudy.quiz.repository.QuizRepository;
import com.toystudy.quiz.request.QuizEdit;
import com.toystudy.quiz.request.QuizRequest;
import com.toystudy.quiz.response.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public void saveQuiz(QuizRequest request) {

        quizRepository.save(Quiz.builder()
                .name(request.getName())
                .category(request.getCategory())
                .content(request.getContent())
//                .keyword(request.getKeyword())
//                .finish(0)
                .build());

    }

    public QuizResponse getQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));

        QuizResponse response = QuizResponse.builder()
                .id(quiz.getId())
                .category(quiz.getCategory())
                .name(quiz.getName())
                .content(quiz.getContent())
//                .keyword(quiz.getKeyword())
//                .finish(quiz.getFinish())
//                .lastSolvedTime(quiz.getLastSolvedTime())
                .build();

        return response;
    }

    public List<QuizResponse> getQuizList(Pageable page) {

        List<QuizResponse> responseList = quizRepository.findAll(page).stream().map(quiz ->
                QuizResponse.builder()
                        .id(quiz.getId())
                        .category(quiz.getCategory())
                        .name(quiz.getName())
                        .content(quiz.getContent())
//                        .keyword(quiz.getKeyword())
//                        .lastSolvedTime(quiz.getLastSolvedTime())
//                        .finish(quiz.getFinish())
                        .build()
        ).collect(Collectors.toList());

        return responseList;
    }

    @Transactional
    public void update(Long id, QuizEdit quizEdit) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 퀴즈입니다."));
        quiz.edit(quizEdit);
    }

    public void delete(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 퀴즈입니다."));

        quizRepository.delete(quiz);
    }
}
