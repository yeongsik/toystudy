package com.toystudy.quiz.response;

import com.toystudy.quiz.domain.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class QuizResponse {

    private Long id;

    private Category category;

    private String name;

    private String content;

    private String keyword;

    private Date lastSolvedTime;

    private Integer finish;
}
