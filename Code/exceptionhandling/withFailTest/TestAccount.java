package be.leerstad.exceptionhandling.withFailTest;

import junit.framework.TestCase;
import org.junit.Test;


public class TestAccount extends TestCase {

    Account hdl;
    Account vdk;

    public TestAccount(String name) {
        super(name);
    }

    public void setUp() {
        hdl = new Account("000.1211295.56", "Hardeel");
        vdk = new Account("000.1478523.47", "Van Dyck");
        hdl.setAmount(900);
        vdk.setAmount(500);
    }

//	public void testDebitEntry() {
//		try {
//			assertEquals("fail in assert test testDebitEntry",true,hdl.debitEntry(900));
//		} catch (BelowZeroException be) {
//			fail("fail in test testDebitEntry catch ");
//		}
//		try {
//			vdk.debitEntry(600);
//			fail("fail in test testDebitEntry");
//		} catch (BelowZeroException be) {
//			assertEquals("fail in test testDebitEntry", "gaat niet door", be.getMessage());
//		}
//
//	}

    @Test(expected = BelowZeroException.class)
    public void testDebitEntry() {
        try {
            vdk.debitEntry(700);
        } catch (BelowZeroException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
