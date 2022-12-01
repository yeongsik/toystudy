package com.toystudy.quiz.domain;

import com.toystudy.quiz.request.QuizEdit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorColumn(name = "QTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@SuperBuilder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    @Lob
    private String answer;

    public Quiz(Category category, String name, String answer) {
        this.category = category;
        this.name = name;
        this.answer = answer;
    }

    public void edit(QuizEdit quizEdit) {
        this.category = quizEdit.getCategory() == null ? this.category : quizEdit.getCategory();
        this.name = quizEdit.getName() == null || quizEdit.getName() == "" ? this.name : quizEdit.getName();
        this.answer = quizEdit.getAnswer() == null || quizEdit.getAnswer() == "" ? this.answer : quizEdit.getAnswer();
    }
}
