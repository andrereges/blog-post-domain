package com.bank.fakeRepository;

import com.bank.model.Account;
import com.bank.repository.Repository;

import java.util.Hashtable;

public class MemoryAccountRepository implements Repository<Account, String> {

    private Hashtable<String, Account> dict;

    public MemoryAccountRepository() {
        dict = new Hashtable<>();
    }

    @Override
    public Account get(String identity) {
        return dict.get(identity);
    }

    @Override
    public void add(Account entity) {
        dict.put(entity.getNumber(), entity);
    }
}
