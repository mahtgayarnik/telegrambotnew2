package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private Long id;
    private Long telegramId;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private Integer level;
    private String wallet;
    private Double balance;
    private String referralLink;
    private String language;
    private Long sponsorId;
    private Long invitedLeftId;
    private Long invitedRightId;
    private Boolean paidBot;
    private Boolean paidSponsor;
    private Long invitingId;
    private Boolean isAnswer;
    private String answer;
    private String userName;
}
