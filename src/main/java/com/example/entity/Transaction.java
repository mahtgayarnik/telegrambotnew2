package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @JsonProperty(value = "transactionType")
    private String transactionType;
    @JsonProperty(value = "orderReference")
    private String orderReference;
    @JsonProperty(value = "createdDate")
    private Long createdDate;
    @JsonProperty(value = "amount")
    private Double amount;
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "transactionStatus")
    private String transactionStatus;
    @JsonProperty(value = "processingDate")
    private Long processingDate;
    @JsonProperty(value = "reasonCode")
    private Integer reasonCode;
    @JsonProperty(value = "reason")
    private String reason;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "phone")
    private String phone;
    @JsonProperty(value = "paymentSystem")
    private String paymentSystem;
    @JsonProperty(value = "cardPan")
    private String cardPan;
    @JsonProperty(value = "cardType")
    private String cardType;
    @JsonProperty(value = "issuerBankCountry")
    private String issuerBankCountry;
    @JsonProperty(value = "issuerBankName")
    private String issuerBankName;
    @JsonProperty(value = "fee")
    private Integer fee;
}
