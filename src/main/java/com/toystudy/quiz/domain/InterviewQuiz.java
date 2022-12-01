package com.toystudy.quiz.domain;

import com.toystudy.quiz.request.QuizEdit;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("interview")
@SuperBuilder
public class InterviewQuiz extends Quiz {

    private String keyword;

    public InterviewQuiz(Category category, String name, String answer, String keyword) {
        super(category, name, answer);
        this.keyword = keyword;
    }

    @Override
    public void edit(QuizEdit quizEdit) {
        super.edit(quizEdit);
        this.keyword = quizEdit.getKeyword();
    }
}
