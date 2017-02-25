package be.leerstad.exceptionhandling.withTryCatchExceptions;

import junit.framework.TestCase;

public class TestBank extends TestCase {

    Bank bank;
    Account hdl;
    Account vdk;

    public TestBank(String name) {
        super(name);
    }

    public void setUp() {
        bank = new Bank();
        hdl = new Account("000.1211295.56", "Hardeel");
        vdk = new Account("000.1478523.47", "Van Dyck");
        bank.addAccount(hdl);
        bank.addAccount(vdk);
        bank.setAmount(hdl, 1000);
        bank.setAmount(vdk, 500);
    }

    public void testDebitEntry() {
        bank.debitEntry(hdl, 500);
        bank.debitEntry(vdk, 600);
        assertEquals("method debit is not correct", 500, bank.getAmount(hdl));
        assertEquals("method debit is not correct", 500, bank.getAmount(vdk));
    }

    public void testaddEntry() {
        bank.addEntry(vdk, 100);
        bank.addEntry(hdl, 1000);
        assertEquals("method transfer is not correct", 2000, bank
                .getAmount(hdl));
        assertEquals("method transfer is not correct", 600, bank.getAmount(vdk));
    }

    public void testTransferEntry() {
        bank.transfer(vdk, hdl, 100000);
        assertEquals("method transfer is not correct", 1000, bank
                .getAmount(hdl));
        assertEquals("method transfer is not correct", 500, bank.getAmount(vdk));
    }

}
