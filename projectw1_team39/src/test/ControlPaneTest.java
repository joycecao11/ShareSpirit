//package test;
//
//import model.AccountList;
//import model.BookList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import ui.ControlPane;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class ControlPaneTest {
//    private ControlPane cp;
//    @BeforeEach
//    public void runBefore(){
//        cp = new ControlPane();
//    }
//
//    @Test
//    public void loadTest() throws IOException{
//        cp.load();
//        AccountList al = cp.getListOfAcc();
//        BookList bl = cp.getListOfBook();
//        assertTrue(bl.contains("book1","1","tim","joyce"));
//        assertTrue(al.contains("joyce","123"));
//        assertTrue(al.contains("mix","456"));
//    }
//
//    @Test
//    public void saveTest() throws IOException{
//        cp.load();
//        assertTrue(cp.addAccount("jimmy","110"));
//        cp.save();
//        List<String> accountLines = Files.readAllLines(Paths.get("account.txt"));;
//        List<String> bookLines = Files.readAllLines(Paths.get("book.txt"));;
//        assertEquals(accountLines.get(0),"joyce 123");
//        assertEquals(accountLines.get(1),"mix 456");
//        assertEquals(accountLines.get(2),"jimmy 110");
//        assertEquals(bookLines.get(0),"book1 1 tim 2 joyce");
//    }
//
//    @Test
//    public void setUserInProgressTest() throws IOException{
//        assertFalse(cp.setUserInProgress("joyce","123"));
//        cp.load();
//        assertTrue(cp.setUserInProgress("joyce","123"));
//    }
//
//    @Test
//    public void getUserInProgressTest() throws IOException{
//        cp.load();
//        assertTrue(cp.setUserInProgress("joyce","123"));
//        assertTrue(cp.getUserInProgress().checkSame("joyce","123"));
//    }
//
//    @Test
//    public void addNewbookTest() throws IOException{
//        cp.load();
//        assertFalse(cp.getListOfBook().contains("book2","2","tim","joyce"));
//        assertTrue(cp.setUserInProgress("joyce","123"));
//        assertTrue(cp.addNewBook("book2","2","tim",2));
//        assertTrue(cp.getListOfBook().contains("book2","2","tim","joyce"));
//    }
//
//    @Test
//    public void addNewAccountTest() throws IOException{
//        assertFalse(cp.getListOfAcc().contains("joyce","123"));
//        assertFalse(cp.getListOfAcc().contains("mix","456"));
//        cp.load();
//        assertTrue(cp.getListOfAcc().contains("joyce","123"));
//        assertTrue(cp.getListOfAcc().contains("mix","456"));
//        assertTrue(cp.addAccount("petter","789"));
//        assertTrue(cp.getListOfAcc().contains("petter","789"));
//    }
//
//    @Test
//    public void deleteBookTest() throws IOException{
//        cp.load();
//        assertTrue(cp.setUserInProgress("joyce","123"));
//        assertTrue(cp.addNewBook("book2","2","tim",2));
//        assertTrue(cp.deleteBook("book1","1","tim"));
//        assertFalse(cp.getListOfBook().contains("book1","1","tim","joyce"));
//        cp.save();
//    }
//
//    @Test
//    public void unsetUserInProgressTest() throws IOException{
//        cp.load();
//        assertTrue(cp.setUserInProgress("joyce","123"));
//        assertTrue(cp.getUserInProgress().checkSame("joyce","123"));
//        cp.unsetUserInProgress();
//        assertEquals(cp.getUserInProgress(),null);
//    }
//}
