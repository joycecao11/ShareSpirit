package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Account;

public class AccoutTest {
   // public static final String INITIAL_STRING = "Default";
    private Account acc;

    @BeforeEach
    public void runBefore(){
        acc = new Account();
    }

    @Test
    public void NoArgumentConstructorTest(){
        assertTrue(acc.getEmail().equals("unknown"));
        assertTrue(acc.getPassword().equals("unknown"));
        assertTrue(acc.getUsername().equals("unknown"));
    }

    @Test
    public void ConstructorTest(){
        Account a = new Account("joyce", "123");
        assertTrue(a.getEmail().equals("joyce"));
        assertTrue(a.getPassword().equals("123"));
        assertTrue(a.getUsername().equals("unknown"));
    }

    @Test
    public void setFunctionsTest(){
        acc.setEmail("gmail");
        assertTrue(acc.getEmail().equals("gmail"));
        acc.setUserName("joy");
        assertTrue(acc.getUsername().equals("joy"));

    }

    @Test
    public void checkSameTest(){
        assertTrue(acc.checkSame("unknown","unknown"));
    }

    @Test
    public void toStringTest(){
        assertTrue(acc.toString().equals("User info: email: unknown username: unknown"));
    }
}
