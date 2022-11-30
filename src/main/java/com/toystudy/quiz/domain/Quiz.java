package com.toystudy.quiz.domain;

import com.toystudy.quiz.request.QuizEdit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    @Lob
    private String content;

//    private String keyword;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastSolvedTime;

//    private Integer finish;

    @Builder
    public Quiz(Category category, String name, String content, String keyword, Date lastSolvedTime, Integer finish) {
        this.category = category;
        this.name = name;
        this.content = content;
//        this.keyword = keyword;
//        this.lastSolvedTime = lastSolvedTime;
//        this.finish = finish;
    }

    public void edit(QuizEdit quizEdit) {
        this.category = quizEdit.getCategory() == null ? this.category : quizEdit.getCategory();
        this.name = quizEdit.getName() == null || quizEdit.getName() == "" ? this.name : quizEdit.getName();
        this.content = quizEdit.getContent() == null || quizEdit.getContent() == "" ? this.content : quizEdit.getContent();
    }
}
