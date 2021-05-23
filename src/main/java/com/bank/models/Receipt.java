package com.bank.models;

import java.util.Random;

public class Receipt {

    private String transactionId;

    public Receipt() {
        Random random = new Random();
        transactionId = String.valueOf(random.ints(100000, 999999)
                .findFirst().getAsInt());
    }

    public String getTransactionId() {
        return transactionId;
    }
}
