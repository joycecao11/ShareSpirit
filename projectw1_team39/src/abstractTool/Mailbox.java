package abstractTool;

import java.util.ArrayList;
import java.util.List;

public abstract class Mailbox {

    protected List<MailingItem> itemList;

    public Mailbox(){
        this.itemList = new ArrayList<>();
    }
    public void emptyBox(){
        this.itemList.clear();
    }

    public String checkBox(){
        String message = "";
        for(int i = 0; i < itemList.size(); i ++) {
            message = message + "" + i + " ";
            message += itemList.get(i).toString();
            message += "\n";
        }
        return message;
    }

    public void receive(MailingItem r){
        //this.hasNewMsg = true;
        itemList.add(r);
    }
}
