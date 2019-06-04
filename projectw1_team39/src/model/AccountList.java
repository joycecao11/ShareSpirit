package model;

import ui.ShareSpirit;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
    private HashMap<String,Account> listOfAccHash;
    private ArrayList<Account> listOfAcc;

    public AccountList(){
        listOfAccHash = new HashMap<>();
        listOfAcc = new ArrayList<>();
    }

    public void setObserver(ShareSpirit ss){
        for(Account a: listOfAcc){
            a.setObserver(ss);
        }
    }

    public ArrayList<Account> getAccountList(){
        //return this.listOfAcc;
        return new ArrayList<>(listOfAccHash.values());
    }

    public boolean addAccount(String email, String password){
        Account temp = new Account(email, password);
        if(!listOfAccHash.containsKey(email)) {
            listOfAccHash.put(email, temp);
            listOfAcc.add(temp);
            return true;
        }
        else{
            return false;
        }

    }

    public Account getAccount (String email, String password){
//        Account temp = new Account(email,password);
//        for(Account a: listOfAcc){
//            if(a.equals(temp)){
//                return a;
//            }
//        }
//        return null;
        if(listOfAccHash.containsKey(email)){
            return listOfAccHash.get(email);
        }
        return null;
    }

    public Account getAccount (String email){
        if(listOfAccHash.containsKey(email)){
            return listOfAccHash.get(email);
        }
        return null;
    }

//    public boolean contains(String email, String password){
//        for(Account a: listOfAcc){
//            if(a.checkSame(email,password)){
//                return true;
//            }
//        }
//        return false;
//    }

    public String toString(){
        String message = "";
        for(Account a: listOfAccHash.values()){
            message += a.toString();
            message += "\n";
        }
        return message;
    }
}
