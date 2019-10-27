package com.example;

import com.example.service.PaymentService;

public class ProcessTransactionsThread extends Thread {

    private PaymentService paymentService = new PaymentService();

    public ProcessTransactionsThread(String name) {
        super(name);
    }

    public void run() {
        while (true) {
            System.out.println("START PROCESS TRANSACTION");
            paymentService.processTransactions(/*Instant.now().getEpochSecond() - 120L, Instant.now().getEpochSecond()*/1454277600L, 1454364000L);
            System.out.println("FINISH PROCESS TRANSACTION");
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
