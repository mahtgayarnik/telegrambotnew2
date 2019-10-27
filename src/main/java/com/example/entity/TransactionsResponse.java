package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponse {

    private String reason;
    private String reasonCode;
    private List<Transaction> transactionList;

}
