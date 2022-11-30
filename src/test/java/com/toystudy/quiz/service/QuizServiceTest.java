package com.toystudy.quiz.service;

import com.toystudy.quiz.domain.Category;
import com.toystudy.quiz.domain.Quiz;
import com.toystudy.quiz.repository.QuizRepository;
import com.toystudy.quiz.request.QuizEdit;
import com.toystudy.quiz.request.QuizRequest;
import com.toystudy.quiz.response.QuizResponse;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class QuizServiceTest {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @BeforeEach
    void clean() {
        quizRepository.deleteAll();
    }

    @Test
    @DisplayName("퀴즈 저장 성공 테스트")
    void saveQuiz () throws Exception {
        //given
        QuizRequest request = QuizRequest.builder()
                .category(Category.DB)
                .name("Key")
                .content("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        //when
        quizService.saveQuiz(request);

        //then
        Assertions.assertEquals(1L,quizRepository.findAll().size());
        Assertions.assertEquals(request.getCategory(),quizRepository.findAll().get(0).getCategory());
        Assertions.assertEquals(request.getName(),quizRepository.findAll().get(0).getName());
        Assertions.assertEquals(request.getContent(),quizRepository.findAll().get(0).getContent());

    }

    @Test
    @DisplayName("퀴즈 단건 조회")
    void GetQuizById() throws Exception {

        //given
        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .content("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        Quiz save = quizRepository.save(request);

        //when
        QuizResponse response = quizService.getQuiz(save.getId());

        //then
        Assertions.assertEquals(save.getId() , response.getId());
        Assertions.assertEquals(Category.DB, response.getCategory());
        Assertions.assertEquals("Key" , response.getName());
        Assertions.assertEquals("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute" , response.getContent());

    }

    @Test
    @DisplayName("퀴즈 다건 조회")
    void GetQuizList () throws Exception {

        //given
        List<Quiz> requestList = IntStream.range(1, 31).mapToObj(i ->
                        Quiz.builder()
                                .category(Category.DB)
                                .name("Key")
                                .content("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                                .build())
                .collect(Collectors.toList());

        quizRepository.saveAll(requestList);

        PageRequest page = PageRequest.of(2, 10, Sort.by(Sort.Direction.DESC, "id"));
        //when
        List<QuizResponse> reponseList = quizService.getQuizList(page);

        //then
        Assertions.assertEquals(10,reponseList.size());
        Assertions.assertEquals(10,reponseList.get(0).getId());
        Assertions.assertEquals(Category.DB,reponseList.get(0).getCategory());
        Assertions.assertEquals("Key",reponseList.get(0).getName());
        Assertions.assertEquals("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute",
                reponseList.get(0).getContent());

    }

    @Test
    @DisplayName("퀴즈 수정")
    void modifyQuizTest () throws Exception {

        //given
        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .content("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        quizRepository.save(request);

        //when

        QuizEdit quizEdit = QuizEdit.builder()
                .category(Category.DB)
                .name("")
                .content("인덱스 설명")
                .build();

        quizService.update(request.getId(), quizEdit);

        //then

        QuizResponse quiz = quizService.getQuiz(request.getId());
        Assertions.assertEquals("Key" , quiz.getName());
        Assertions.assertEquals("인덱스 설명" , quiz.getContent());

    }

    @Test
    @DisplayName("퀴즈 삭제")
    void deleteQuizTest () throws Exception {
        //given
        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .content("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        quizRepository.save(request);

        //when
        quizService.delete(request.getId());

        //then
        Assertions.assertEquals(0,quizRepository.count());
    }
}