package com.toystudy.quiz.request;

import com.toystudy.annotation.ValidEnum;
import com.toystudy.quiz.domain.*;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class QuizRequest{

    @ValidEnum(enumClass = Category.class, message = "제대로된 카테고리가 입력되지 않았습니다.")
    private Category category;
    @ValidEnum(enumClass = Qtype.class, message = "문제유형이 설정되지 않았습니다.")
    private Qtype qtype;
    @NotBlank(message = "문제 이름이 입력되지 않았습니다.")
    private String name;
    @NotBlank(message = "정답이 입력되지 않았습니다.")
    private String answer;

    private String example1;
    private String example2;
    private String example3;
    private String example4;
    private String example5;
    private String keyword;

    public Quiz convertQuiz() {
        switch (this.qtype) {
            case SHORT_FORM:
                return ShortFormQuiz.builder()
                        .category(category)
                        .name(name)
                        .answer(answer)
                        .build();
            case INTERVIEW:
                return InterviewQuiz.builder()
                        .category(category)
                        .name(name)
                        .answer(answer)
                        .keyword(keyword)
                        .build();
            case MUlTIPLE:
                return MultipleChoiceQuiz.builder()
                        .category(category)
                        .name(name)
                        .answer(answer)
                        .example1(example1)
                        .example2(example2)
                        .example3(example3)
                        .example4(example4)
                        .example5(example5)
                        .build();
        }

        return null;
    }

}
