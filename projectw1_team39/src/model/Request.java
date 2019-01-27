package model;

import abstractTool.MailingItem;

public class Request extends MailingItem {
    public Request(Book b, String newSender){
        this.sender = newSender;
        this.receiver = b.getAccount();
        this.book = b;
    }

    public String toString(){
        return book.toString() + " " + this.sender;
    }
}
