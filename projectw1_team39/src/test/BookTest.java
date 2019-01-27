package test;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class BookTest {
    public static final String INITIAL_STRING = "Default";
    private Book book;

    @BeforeEach
    public void runBefore(){
        book = new Book(INITIAL_STRING,"1",INITIAL_STRING,1,INITIAL_STRING);
    }

    @Test
    public void constructorTest(){
        assertTrue(book.getBookName().equals(INITIAL_STRING));
        assertTrue(book.getAuthorName().equals(INITIAL_STRING));
        assertEquals(book.getEdition(),"1");
        assertEquals(book.getPrice(),1);
    }

    @Test
    public void checkSameTest(){
        assertTrue(book.checkSame(INITIAL_STRING,"1",INITIAL_STRING,INITIAL_STRING));
        assertFalse(book.checkSame(INITIAL_STRING,"2",INITIAL_STRING,INITIAL_STRING));
        assertFalse(book.checkSame("","1",INITIAL_STRING,INITIAL_STRING));
        assertFalse(book.checkSame(INITIAL_STRING,"1","",INITIAL_STRING));
        assertFalse(book.checkSame(INITIAL_STRING,"1",INITIAL_STRING,""));
    }
}
