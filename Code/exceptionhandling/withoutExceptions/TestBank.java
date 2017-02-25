package be.leerstad.exceptionhandling.withoutExceptions;

import junit.framework.TestCase;


public class TestBank extends TestCase {

    Bank bank;
    Account vsn;
    Account vrt;

    public TestBank(String name) {
        super(name);
    }

    public void setUp() {
        bank = new Bank();
        vsn = new Account("000.1211295.56", "Van Severen");
        vrt = new Account("000.1478523.47", "Van Roste");
        bank.addAccount(vsn);
        bank.addAccount(vrt);
        bank.setAmount(vsn, 1000);
        bank.setAmount(vrt, 500);
    }

    public void testDebitEntry() {
        bank.debitEntry(vsn, 500);
        bank.debitEntry(vrt, 600);
        assertEquals("method debit is not correct", 500, bank.getAmount(vsn));
        assertEquals("method debit is not correct", -100, bank.getAmount(vrt));
    }

    public void testaddEntry() {
        bank.addEntry(vrt, 100);
        bank.addEntry(vsn, 1000);
        assertEquals("method transfer is not correct", 2000, bank
                .getAmount(vsn));
        assertEquals("method transfer is not correct", 600, bank.getAmount(vrt));
    }

    public void testTransferEntry() {
        bank.transfer(vrt, vsn, 100000);
        assertEquals("method transfer is not correct", 101000, bank
                .getAmount(vsn));
        assertEquals("method transfer is not correct", -99500, bank
                .getAmount(vrt));
    }

}
