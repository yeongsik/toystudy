package com.toystudy.quiz.domain;

import com.toystudy.quiz.request.QuizEdit;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("multiple")
@SuperBuilder
public class MultipleChoiceQuiz extends Quiz {

    private String example1;
    private String example2;
    private String example3;
    private String example4;
    private String example5;

    public MultipleChoiceQuiz(Category category, String name, String answer, String example1, String example2, String example3, String example4, String example5) {
        super(category, name, answer);
        this.example1 = example1;
        this.example2 = example2;
        this.example3 = example3;
        this.example4 = example4;
        this.example5 = example5;
    }

    @Override
    public void edit(QuizEdit quizEdit) {
        super.edit(quizEdit);
        this.example1 = quizEdit.getExample1();
        this.example2 = quizEdit.getExample2();
        this.example3 = quizEdit.getExample3();
        this.example4 = quizEdit.getExample4();
        this.example5 = quizEdit.getExample5();
    }
}
