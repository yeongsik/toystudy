package com.toystudy.quiz.response;

import com.toystudy.quiz.domain.Category;
import com.toystudy.quiz.request.Qtype;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class QuizResponse {

    private Long id;
    private Category category;
    private Qtype qtype;
    private String name;
    private String answer;
    private String example1;
    private String example2;
    private String example3;
    private String example4;
    private String example5;
    private String keyword;

    @Builder
    public QuizResponse(Long id, Category category, Qtype qtype, String name, String answer, String example1, String example2, String example3, String example4, String example5, String keyword) {
        this.id = id;
        this.category = category;
        this.qtype = qtype;
        this.name = name;
        this.answer = answer;
        this.example1 = example1;
        this.example2 = example2;
        this.example3 = example3;
        this.example4 = example4;
        this.example5 = example5;
        this.keyword = keyword;
    }
}
