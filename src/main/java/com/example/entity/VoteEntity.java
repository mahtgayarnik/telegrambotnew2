package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteEntity {

    private Long id;
    private String question;
    private String answerOne;
    private Integer countOne;
    private String answerTwo;
    private Integer countTwo;
    private String answerThree;
    private Integer countThree;
    private String answerFour;
    private Integer countFour;
    private String answerFive;
    private Integer countFive;
    private Boolean active;
    private String vote;
}
