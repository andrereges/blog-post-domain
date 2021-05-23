package com.bank.repository;

public interface Repository<T, I> {
    T get(I identity);
    void add(T entity);
}
