package com.bank.services;

import com.bank.models.Account;
import com.bank.models.Receipt;

public class AccountService {

    public Receipt transfer(Account accountFrom, Account accountTo, Double value) {
        if (accountFrom == null || accountTo == null)
            throw new IllegalArgumentException("AccountFrom or AccountTo cannot be null");

        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        if (value <= 0)
            throw new IllegalArgumentException("Value must be more zero");

        accountFrom.withdraw(value);
        accountTo.deposit(value);

        return new Receipt();
    }

}
