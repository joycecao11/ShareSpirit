package model;

import abstractTool.Mailbox;
import abstractTool.MailingItem;
import exceptionTool.OutOfListException;

public class RequestBox extends Mailbox {

    public RequestBox(){
        super();
    }

    public MailingItem getRequest(int index) throws OutOfListException {
        if(index < 0 || index >= itemList.size()){
            throw new OutOfListException("No request has such number");
        }
        return itemList.get(index);
    }
}
