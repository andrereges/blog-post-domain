package com.bank.services;

import com.bank.models.Account;
import com.bank.models.Receipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountServiceTest {

    private AccountService accountService;

    public AccountServiceTest() {
        accountService = new AccountService();
    }

    @Test
    public void testTransferMoneySuccess() {
        Account accountFrom = new Account("123456", 2000d);
        Account accountTo = new Account("654321", 3000d);

        accountService.transfer(accountFrom, accountTo, 200d);

        Assertions.assertEquals(1800d, accountFrom.getBalance());
        Assertions.assertEquals(3200d, accountTo.getBalance());
    }

    @Test
    public void testTransferMoneyWithReceiptSuccess() {
        Receipt receipt = accountService.transfer(
            new Account("123456", 2000d),
            new Account("654321", 3000d),
            200d);

        Assertions.assertEquals(6, receipt.getTransactionId().length());
    }

    @Test
    public void testTransferMoneyFailureAccountFromNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> accountService.transfer(
                    null,
                    new Account("654321", 3000d),
                    200d)
        );
    }

    @Test
    public void testTransferMoneyFailureAccountToNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> accountService.transfer(
                        new Account("123456", 2000d),
                        null,
                        200d)
        );
    }

    @Test
    public void testTransferMoneyFailureAccountFromAndAccountToNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> accountService.transfer(
                        null,
                        null,
                        200d)
        );
    }

    @Test
    public void testTransferMoneyFailureAccountFromAndAccountToAndValueNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> accountService.transfer(
                        null,
                        null,
                        0d)
        );
    }

    @Test
    public void testTransferMoneyFailureZeroValue() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> accountService.transfer(
                        new Account("123456", 2000d),
                        new Account("654321", 3000d),
                        0d)
        );
    }

    @Test
    public void testTransferMoneyFailureNullValue() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> accountService.transfer(
                        new Account("123456", 2000d),
                        new Account("654321", 3000d),
                        null)
        );
    }
}
