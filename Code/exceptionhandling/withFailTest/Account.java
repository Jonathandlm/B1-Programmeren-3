package be.leerstad.exceptionhandling.withFailTest;

public class Account {
    private String accountNumber;
    private String holder;
    private int amount;

    public Account(String accountNumber, String holder) {
        this.accountNumber = accountNumber;
        this.holder = holder;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean debitEntry(int debitAmount) throws BelowZeroException {
        if (amount >= debitAmount) {

            amount -= debitAmount;
            return true;
        } else
            throw new BelowZeroException("gaat niet door", amount);
    }

    public boolean addEntry(int addAmount) {
        amount += addAmount;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Account))
            return false;
        Account other = (Account) obj;
        if (accountNumber == null) {
            if (other.accountNumber != null)
                return false;
        } else if (!accountNumber.equals(other.accountNumber))
            return false;
        return true;
    }
}
