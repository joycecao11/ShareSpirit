package model;

import java.util.Objects;

public class Book {
    private static final String DEFAULT_SETTING = "unknown";
    private String bookName;
    private String edition;
    private String author;
    private double price;
    private String account;

    //REQUIRES: price >= 0
    // MODIFIES: this
    // EFFECTS: construct a new Account object with fields being set to be given course name
    public Book(String bookName, String edition, String author, double price, String acc){
        this.bookName = bookName;
        this.edition = edition;
        this.author = author;
        this.price = price;
        this.account = acc;
    }

    // EFFECTS: return the string that describe the book
    public String toString(){
        return this.bookName + " " + this.edition + " " + this.author + " " + this.price + " " + this.account;
    }

    // EFFECTS: return the book name of the book
    public String getBookName(){
        return this.bookName;
    }

    // EFFECTS: return the edition number of the book
    public String getEdition(){
        return this.edition;
    }

    // EFFECTS: return the author name of the book
    public String getAuthorName(){
        return this.author;
    }

    // EFFECTS: return the price of the book
    public double getPrice(){
        return this.price;
    }

    public String getAccount(){
        return this.account;
    }

    // EFFECTS: return true if the course name is as same as the given name, false otherwise
    public boolean checkSame(String bookN,String edition,String authorN,String acc){
        if(bookN.equals(this.bookName) && edition.equals(this.edition) && authorN.equals(this.author) && acc.equals(this.account)){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName) &&
                Objects.equals(edition, book.edition) &&
                Objects.equals(author, book.author) &&
                Objects.equals(account, book.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookName, edition, author, account);
    }
}
