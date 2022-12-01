package com.toystudy.quiz.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("short")
@SuperBuilder
public class ShortFormQuiz extends Quiz{

    public ShortFormQuiz() {

    }
    public ShortFormQuiz(Category category, String name, String answer) {
        super(category, name, answer);
    }
}
