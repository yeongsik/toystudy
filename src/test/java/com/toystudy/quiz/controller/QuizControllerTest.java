package com.toystudy.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toystudy.quiz.domain.Category;
import com.toystudy.quiz.domain.Quiz;
import com.toystudy.quiz.repository.QuizRepository;
import com.toystudy.quiz.request.QuizEdit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AutoConfigureMockMvc
@SpringBootTest
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("퀴즈 저장 성공 테스트")
    void saveQuiz () throws Exception {

        //given
        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .answer("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected

        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("퀴즈 저장 데이터 검증 테스트")
    void validRequestTest () throws Exception {

        //given
        Quiz request = Quiz.builder()
                .category(null)
                .name("")
                .answer("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();


        String json = objectMapper.writeValueAsString(request);
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.category").value("제대로된 카테고리가 입력되지 않았습니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.name").value("개념이 입력되지 않았습니다."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 다건 조회 페이징 처리 테스트")
    void getListByPagination() throws Exception {

        //given
        List<Quiz> requestList = IntStream.range(1, 31).mapToObj(i ->
                        Quiz.builder()
                                .category(Category.DB)
                                .name("Key")
                                .answer("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                                .build())
                .collect(Collectors.toList());

        quizRepository.saveAll(requestList);

        //expected

        mockMvc.perform(MockMvcRequestBuilders.get("/quizzes?page=2&size=10&sort=id,DESC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 수정 테스트")
    void modifyQuizTest () throws Exception {

        //given

        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .answer("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        QuizEdit quizEdit = QuizEdit.builder()
                .category(Category.JAVA)
                .name("String")
                .answer("스트링 내용")
                .build();

        quizRepository.save(request);

        //expected

        mockMvc.perform(MockMvcRequestBuilders.patch("/quizzes/{quizId}", request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("퀴즈 삭제 테스트")
    void deleteQuizTest () throws Exception {

        //given
        Quiz request = Quiz.builder()
                .category(Category.DB)
                .name("Key")
                .answer("검색, 정렬시 Tuple을 구분할 수 있는 기준이 되는 Attribute")
                .build();

        quizRepository.save(request);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.delete("/quizzes/{quizId}", request.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}