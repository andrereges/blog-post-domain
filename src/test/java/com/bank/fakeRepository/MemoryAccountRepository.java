package com.bank.fakeRepository;

import com.bank.model.Account;
import com.bank.repository.Repository;

import java.util.Hashtable;

public class MemoryAccountRepository implements Repository<Account, String> {

    private Hashtable<String, Account> hashtable;

    public MemoryAccountRepository() {
        hashtable = new Hashtable<>();
    }

    @Override
    public Account get(String identity) {
        return hashtable.get(identity);
    }

    @Override
    public void add(Account entity) {
        hashtable.put(entity.getNumber(), entity);
    }
}
