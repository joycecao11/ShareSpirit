package abstractTool;

import model.Book;

public abstract class MailingItem {
    protected String sender;
    protected String receiver;
    protected Book book;

    public Book getBook(){
        return this.book;
    }

    public String getSender(){
        return this.sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public abstract String toString();
}
