package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntity {

    private Long id;
    private String nameTime;
    private String startTime;
    private String endTime;
}
