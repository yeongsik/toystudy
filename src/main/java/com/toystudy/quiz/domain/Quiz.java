package com.toystudy.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Quiz {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    @Lob
    private String content;

    private String keyword;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSolvedTime;

    private Integer finish;

}
