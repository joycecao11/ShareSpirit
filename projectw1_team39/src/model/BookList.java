package model;

import exceptionTool.NoAuthorityException;
import exceptionTool.OutOfListException;

import java.util.ArrayList;
import java.util.Iterator;

public class BookList {
    private ArrayList<Book> listOfBook;

    public BookList(){
        listOfBook = new ArrayList<>();
    }

    public ArrayList<Book> getBookList(){
        return listOfBook;
    }

    public Book getBook(String bookN,String edition,String authorN,String ownerN){
        Book temp = new Book(bookN,edition,authorN,0,ownerN);
        for(Book b: listOfBook){
            if(b.equals(temp)){
                return b;
            }
        }
        return null;
    }

    public Book getBook(int index) throws OutOfListException {
        if(index < 0 || index >= listOfBook.size()){
            throw new OutOfListException("No book has such a number");
        }
        return listOfBook.get(index);
    }

    public boolean addNewBook(String bookN, String edition, String authorN, double price, String accEmail){
        Book temp = new Book(bookN,edition,authorN,price,accEmail);
        if(!listOfBook.contains(temp)) {
            listOfBook.add(temp);
            return true;
        }
        else{
            return false;
        }
    }

    public void deleteBook(int i,String acc) throws OutOfListException, NoAuthorityException {
        if(i < 0 || i >= listOfBook.size()){
            throw new OutOfListException("No book with such number");
        }
        if(!acc.equals(listOfBook.get(i).getAccount())){
            throw new NoAuthorityException("This is not your book");
        }
        listOfBook.remove(i);
    }

    public void deleteBook(Book target){
        Iterator<Book> itr = listOfBook.iterator();
        while(itr.hasNext()) {
            if (target.equals(itr.next())) {
                itr.remove();
            }
        }
    }

//    public Book deleteBook(String bookN, String edition, String authorN, String acc){
//        int index = listOfBook.size();
//        for(int i = 0; i < listOfBook.size(); i ++){
//            if(listOfBook.get(i).checkSame(bookN,edition,authorN,acc)){
//                index = i;
//                break;
//            }
//        }
//        if(index != listOfBook.size()){
//            Book temp = listOfBook.get(index);
//            listOfBook.remove(index);
//            return temp;
//        }
//        else{
//            return null;
//        }
//    }

//    public boolean contains(String bookN, String edition, String authorN, String acc){
//        for(Book b: listOfBook){
//            if(b.checkSame(bookN,edition,authorN,acc)){
//                return true;
//            }
//        }
//        return false;
//    }

    public String toString(){
        String message = "";
        for(int i = 0; i < listOfBook.size(); i ++){
            message = message + "" + i + " ";
            message += listOfBook.get(i).toString();
            message += "\n";
        }
        return message;
    }
}
