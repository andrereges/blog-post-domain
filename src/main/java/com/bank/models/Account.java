package com.bank.models;

public class Account {
    private String number;
    private Double balance = 0d;

    public Account(String number, Double balance) {
        if (number == null)
            throw new IllegalArgumentException("Account number cannot be null");

        if (number.isEmpty())
            throw new IllegalArgumentException("Account number cannot be empty");

        if (number.length() != 6)
            throw new IllegalArgumentException("Number must be 6 digits");

        if (balance == null)
            throw new IllegalArgumentException("Account balance cannot be null");

        if (balance < 0)
            throw new IllegalArgumentException("Balance must be greater than or equal to zero");

        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public void deposit(Double value) {
        validateValue(value);

        balance += value;
    }

    public void withdraw(Double value) {
        validateValue(value);

        if (balance < value)
            throw new IllegalArgumentException("Value must be more zero");

        balance -= value;
    }

    private void validateValue(Double value) {
        if (value <= 0d)
            throw new IllegalArgumentException("Value must be more zero");
    }
    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
