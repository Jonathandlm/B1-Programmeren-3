package be.leerstad.exceptionhandling.withoutExceptions;

import java.util.HashSet;
import java.util.Set;

public class Bank {
    private Set<Account> accounts;

    public Bank() {
        accounts = new HashSet<Account>();
    }

    public void setAmount(Account account, int amount) {
        account.setAmount(amount);
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public boolean debitEntry(Account account, int debitAmount) {
        account.debitEntry(debitAmount);
        return true;
    }

    public boolean transfer(Account debitTransfer, Account addTransfer, int amount) {
        addTransfer.addEntry(amount);
        debitTransfer.debitEntry(amount);
        return true;
    }

    public boolean addEntry(Account account, int addAmount) {
        account.addEntry(addAmount);
        return true;
    }

    public int getAmount(Account account) {
        return account.getAmount();
    }

}
