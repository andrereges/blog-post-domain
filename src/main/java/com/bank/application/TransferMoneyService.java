package com.bank.application;

import com.bank.dto.TransferDto;
import com.bank.model.Account;
import com.bank.model.Receipt;
import com.bank.model.service.TransferMoney;
import com.bank.repository.Repository;

public class TransferMoneyService {

    private Repository<Account, String> repository;

    public TransferMoneyService(Repository<Account, String> repository) {
        this.repository = repository;
    }

    public String transfer(TransferDto dto) {
        Account accountFrom = repository.get(dto.getAccountFrom());
        Account accountTo = repository.get(dto.getAccountTo());

        TransferMoney modelTransfer = new TransferMoney();
        Receipt receipt = modelTransfer.transfer(accountFrom, accountTo, dto.getValue());

        repository.add(accountFrom);
        repository.add(accountTo);

        return receipt.getTransactionId();
    }
}
