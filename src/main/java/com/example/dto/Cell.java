package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    private Long id;
    private Long leftId;
    private Long rightId;
    private Long telegramId;
    private Boolean paidBot;
    private Boolean paidSponsor;
}
