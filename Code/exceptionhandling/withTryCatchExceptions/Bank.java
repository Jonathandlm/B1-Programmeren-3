package be.leerstad.exceptionhandling.withTryCatchExceptions;

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
        } catch (TooHighDebetException e) {
            e.printStackTrace();
        } catch (BelowZeroException e) {
            e.printStackTrace();
        }

//        try {
//            account.debitEntry(debitAmount);
//        } catch (TooHighDebetException e1) {
//            System.out.println(e1.getMessage());
//            System.out.println(e1.getStackTrace());
//
//        } catch (BelowZeroException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getSaldo());
//            System.out.println(e.getStackTrace());
//        }
        return true;
    }

    public boolean transfer(Account debitTransfer, Account addTransfer,
                            int amount) {
        boolean ok = false;
        addTransfer.addEntry(amount);
        try {
            debitTransfer.debitEntry(amount);
        } catch (TooHighDebetException e1) {
            System.out.println(e1.getMessage());
            System.out.println(e1.getStackTrace());

        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getSaldo());
        }
        ok = true;

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
