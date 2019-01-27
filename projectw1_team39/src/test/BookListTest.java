package test;

//import model.BookList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class BookListTest {
//    public static final String INITIAL_STRING = "Default";
//    private BookList bl;
//    @BeforeEach
//    public void runBefore(){
//        bl = new BookList();
//    }
//
//    @Test
//    public void addBookTest(){
//        assertFalse(bl.contains(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,INITIAL_STRING));
//        assertTrue(bl.addNewBook(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,1,INITIAL_STRING));
//        assertFalse(bl.addNewBook(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,1,INITIAL_STRING));
//        assertTrue(bl.contains(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,INITIAL_STRING));
//    }
//
//    @Test
//    public void deleteBookTest(){
//        assertEquals(bl.deleteBook(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,INITIAL_STRING),null);
//        assertTrue(bl.addNewBook(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,1,INITIAL_STRING));
//        assertTrue(bl.deleteBook(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,INITIAL_STRING).checkSame(INITIAL_STRING,INITIAL_STRING,INITIAL_STRING,INITIAL_STRING));
//    }
//}
