package abstractTool;

import ui.ShareSpirit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Mailbox extends Observable {

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
        notifyObservers(r.getReceiver());
    }

    public void setObserver(ShareSpirit ss){
        addObserver(ss);
    }
}
