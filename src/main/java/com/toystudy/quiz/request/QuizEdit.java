package com.toystudy.quiz.request;

import com.toystudy.annotation.ValidEnum;
import com.toystudy.quiz.domain.Category;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuizEdit {

    @ValidEnum(enumClass = Category.class, message = "제대로된 카테고리가 입력되지 않았습니다.")
    private Category category;

    @NotBlank(message = "개념이 입력되지 않았습니다.")
    private String name;

    @NotBlank(message = "내용이 입력되지 않았습니다.")
    private String content;

    @Builder
    public QuizEdit(Category category, String name, String content) {
        this.category = category;
        this.name = name;
        this.content = content;
    }
}
