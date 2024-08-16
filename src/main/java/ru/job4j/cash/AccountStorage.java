package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Account fromTransfer;
        Account toTransfer;
        Optional<Account> firstAccountInOptinal = getById(fromId);
        Optional<Account> secondAccountInOptinal = getById(toId);
        if (firstAccountInOptinal.isPresent() && secondAccountInOptinal.isPresent()) {
            if (firstAccountInOptinal.get().amount() >= amount) {
                fromTransfer = new Account(fromId, firstAccountInOptinal.get().amount() - amount);
                toTransfer = new Account(toId, secondAccountInOptinal.get().amount() + amount);
                this.update(fromTransfer);
                this.update(toTransfer);
                result = this.update(fromTransfer) && this.update(toTransfer);
            }
        }
        return result;
    }
}
