package com.bank.application;

import com.bank.dto.TransferDto;
import com.bank.fakeRepository.MemoryAccountRepository;
import com.bank.model.Account;
import com.bank.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransferMoneyServiceTest {

    Repository<Account, String> repository;
    TransferMoneyService appService;
    TransferDto dto;

    public TransferMoneyServiceTest() {
        // Banco de dados fake em memória com valores
        repository = new MemoryAccountRepository();
        repository.add(new Account("123456", 2000d));
        repository.add(new Account("654321", 3000d));

        appService = new TransferMoneyService(repository);
        dto = new TransferDto();
    }

    @Test
    public void testTransferMoneyServiceSuccess() {
        //Arrange
        dto.setAccountFrom("123456");
        dto.setAccountTo("654321");
        dto.setValue(100d);

        // Act
        String receipt = appService.transfer(dto);

        // Assert
        Assertions.assertEquals(6, receipt.length());
        Assertions.assertEquals(repository.get("123456").getBalance(), 1900d);
        Assertions.assertEquals(repository.get("654321").getBalance(), 3100d);
    }
}
