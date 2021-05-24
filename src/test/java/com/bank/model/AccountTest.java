package com.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account createAccount() {
        return new Account("123456", 2000d);
    }

    private Account createAccount(String number, Double balance) {
        return new Account(number, balance);
    }

    @Test
    public void testAccountSuccess() {
        Assertions.assertEquals("123456", createAccount().getNumber());
        Assertions.assertEquals(2000d, createAccount().getBalance());
    }

    @Test
    public void testAccountSuccessNumberWith6Digits() {
        Assertions.assertDoesNotThrow(() -> createAccount());
    }

    @Test
    @DisplayName("Conta com número vazio ou nulo")
    public void testAccountFailureNumberEmptyOrNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("", 2000d));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount(null, 2000d));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount(null, null));
    }

    @Test
    public void testAccountFailureBalanceNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("123456", null));
    }

    @Test
    public void testAccountFailureNumberLess6Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("12345", 2000d));
    }

    @Test
    public void testAccountFailureNumberMore6Digits() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("1234567", 2000d));
    }

    @Test
    public void testAccountBalanceLessZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createAccount("123456", -1d));
    }

    @Test
    public void testDepositSuccess() {
        Account account = createAccount();

        account.deposit(100d);

        Assertions.assertEquals(2100d, account.getBalance());
    }

    @Test
    public void testDepositFailureValueZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                createAccount().deposit(0d));
    }

    @Test
    public void testDepositFailureNegativeValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                createAccount().deposit(-5d));
    }

    @Test
    public void testWithdrawSuccess() {
        Account account = createAccount();

        account.withdraw(100d);

        Assertions.assertEquals(1900d, account.getBalance());
    }

    @Test
    public void testWithdrawFailureValueZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                createAccount().withdraw(0d));
    }

    @Test
    public void testWithdrawFailureNegativeValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                createAccount().withdraw(-5d));
    }

    @Test
    public void testWithdrawFailureUnderLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                createAccount().withdraw(2001d));
    }

    @Test
    public void testAccountToStringSuccess() {
        Account account = createAccount();

        Assertions.assertEquals("Account{" +
                "number='" + account.getNumber() + '\'' +
                ", balance=" + account.getBalance() +
                '}', account.toString());
    }
}
