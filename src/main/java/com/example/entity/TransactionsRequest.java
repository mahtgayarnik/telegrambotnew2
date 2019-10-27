package com.example.entity;

import com.example.connection.MyProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionsRequest {

    @JsonProperty(value = "apiVersion")
    private Integer apiVersion;
    @JsonProperty(value = "transactionType")
    private String transactionType;
    @JsonProperty(value = "origAcc")
    private String merchantAccount;
    @JsonProperty(value = "origMerchantSignatureKey")
    private String merchantSignature;
    @JsonProperty(value = "dateBegin")
    private Long dateBegin;
    @JsonProperty(value = "dateEnd")
    private Long dateEnd;
    @JsonIgnore
    private MyProperties myProperties;

    public TransactionsRequest(){
//        myProperties = MyProperties.getInstance();
//        apiVersion = Integer.valueOf(myProperties.getProperties().getProperty("apiVersion"));
//        apiVersion = Integer.valueOf(myProperties.getProperties().getProperty("1"));
        apiVersion = Integer.valueOf("1");
//        transactionType = myProperties.getProperties().getProperty("transactionType");
        transactionType = "TRANSACTION_LIST";
//        merchantAccount = myProperties.getProperties().getProperty("origAcc");
        merchantAccount = "freelance_user_5d8e8c184e856";
    }
}
