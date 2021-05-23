package com.bank.application;

import com.bank.dto.TransferDto;
import com.bank.fakeRepository.MemoryAccountRepository;
import com.bank.model.Account;
import com.bank.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TransferMoneyServiceTest {

    Repository<Account, String> repository;
    Repository<Account, String> mockitoRepository;
    TransferMoneyService appService;
    TransferDto dto;
    Account accountFrom;
    Account accountTo;

    public TransferMoneyServiceTest() {
        // Banco de dados fake em memória com valores
        // Dublê FAKE - inteligente para lógica, imita muito bem o banco de dados
        repository = new MemoryAccountRepository();
        repository.add(new Account("123456", 2000d));
        repository.add(new Account("654321", 3000d));

        // Dublê MOCK - burro, entrada/saída
        mockitoRepository = (Repository<Account, String>) mock(Repository.class);
        when(mockitoRepository.get("123456")).thenReturn(new Account("123456", 2000d));
        when(mockitoRepository.get("654321")).thenReturn(new Account("654321", 1000d));

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
        Assertions.assertEquals(1900d, repository.get("123456").getBalance());
        Assertions.assertEquals(3100d, repository.get("654321").getBalance());
    }

    @Test
    public void testTransferMoneyServiceSuccessMockito() {
        //Arrange
        dto.setAccountFrom("123456");
        dto.setAccountTo("654321");
        dto.setValue(100d);

        // Act
        String receipt = appService.transfer(dto);

        // Assert
        Assertions.assertEquals(6, receipt.length());
        Assertions.assertEquals(2000d, mockitoRepository.get("123456").getBalance());

        // Espião - funciona como se fosse um assert
        // Verifica se o componente transferMoneyService interage do jeito que eu quero com o componente repository
        verify(mockitoRepository, times(1)).get("123456");
    }
}
