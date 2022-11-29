package com.toystudy.quiz.request;

import com.toystudy.annotation.ValidEnum;
import com.toystudy.quiz.domain.Category;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Getter
public class QuizRequest {

    @ValidEnum(enumClass = Category.class, message = "제대로된 카테고리가 입력되지 않았습니다.")
    private Category category;

    @NotBlank(message = "개념이 입력되지 않았습니다.")
    private String name;

    @NotBlank(message = "내용이 입력되지 않았습니다.")
    private String content;

    @NotBlank(message = "빈칸 키워드가 입력되지 않았습니다.")
    private String keyword;

    private Date lastSolvedTime;

    private Integer finish;

}
