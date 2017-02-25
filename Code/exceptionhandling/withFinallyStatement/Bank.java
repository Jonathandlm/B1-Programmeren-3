package be.leerstad.exceptionhandling.withFinallyStatement;

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
        try {
            account.debitEntry(debitAmount);
        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean transfer(Account debitTransfer, Account addTransfer,
                            int amount) {
        boolean ok = false;
        addTransfer.addEntry(amount);
        try {
            debitTransfer.debitEntry(amount);
            ok = true;
        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
        } finally {
            if (!ok) {
                try {
                    addTransfer.debitEntry(amount);
                } catch (BelowZeroException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
        return ok;
    }

    public boolean addEntry(Account account, int addAmount) {
        account.addEntry(addAmount);
        return true;
    }

    public int getAmount(Account account) {
        return account.getAmount();
    }

}
