//package test;
//
//import model.AccountList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AccountListTest {
//    private AccountList al;
//    @BeforeEach
//    public void runBefore(){
//        al = new AccountList();
//    }
//
//    @Test
//    public void addAccountTest(){
//        boolean add = al.addAccount("joyce","123");
//        assertTrue(al.contains("joyce","123"));
//        assertFalse(al.addAccount("joyce","123"));
//    }
//
//    @Test
//    public void getAccountTest(){
//        assertEquals(al.getAccount("joyce","123"),null);
//        boolean add = al.addAccount("joyce","123");
//        assertTrue(al.contains("joyce","123"));
//        assertTrue(al.getAccount("joyce","123").checkSame("joyce","123"));
//    }
//}
