package model;

import abstractTool.MailingItem;

public class Response extends MailingItem {
    private boolean deal;
    public Response(Book b, String receiver, boolean agree){
        this.deal = agree;
        this.sender = b.getAccount();
        this.receiver = receiver;
        this.book = b;
    }

    public boolean getOpinion(){
        return deal;
    }

    public String toString(){
        if(deal){
            return book.toString() + " " + this.receiver + " " + "true";
        }
        return book.toString() + " " + this.receiver + " " + "false";
    }
}
