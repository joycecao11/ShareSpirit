package model;
import abstractTool.MailingItem;
import exceptionTool.OutOfListException;
import ui.ShareSpirit;

import java.util.*;

public class Account {
    private static final String DEFAULT_SETTING = "unknown";
    private String userName;
    private String email;
    private String password;

    public RequestBox getRequestBox() {
        return requestBox;
    }

    private RequestBox requestBox;
    private ResponseBox responseBox;

    // MODIFIES: this
    // EFFECTS: construct a new Account object with fields being set to be default
    public Account(){
        this.userName  = this.password = this.email = DEFAULT_SETTING;
        requestBox = new RequestBox();
        responseBox = new ResponseBox();
    }

    // MODIFIES: this
    // EFFECTS: construct a new Account object with fields being set to be given value
    public Account(String newEmail, String newPassword){
        this.email = newEmail;
        this.password = newPassword;
        this.userName = DEFAULT_SETTING;
        requestBox = new RequestBox();
        responseBox = new ResponseBox();
    }

    public void setObserver(ShareSpirit ss){
        requestBox.setObserver(ss);
        responseBox.setObserver(ss);
    }

    public boolean checkSame(String newEmail, String newPIN){
        if(newEmail.equals(this.email) && newPIN.equals(this.password)){
            return true;
        }
        return false;
    }

    public void emptyRequestBox(){
        this.requestBox.emptyBox();
    }

    public void emptyResponseBox(){
        this.responseBox.emptyBox();
    }

    public void receiveRequest(Request r){
           requestBox.receive(r);
    }

    public void receiveResponse(Response r){
        responseBox.receive(r);
    }

    public MailingItem getRequest(int index) throws OutOfListException {
        return requestBox.getRequest(index);
    }

    public String checkRequestBox(){
        return requestBox.checkBox();
    }

    public String checkResponseBox(){
        return responseBox.checkBox();
    }

    public boolean checkSame(String newEmail){
        if(newEmail.equals(this.email)){
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: change user name of the account
    public void setUserName(String newUserName){
        this.userName = newUserName;
    }

    // MODIFIES: this
    // EFFECTS: change email of the account
    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    // EFFECTS: return email address
    public String getEmail(){
        return this.email;
    }

    // EFFECTS: return user name
    public String getUsername(){
        return this.userName;
    }

    // EFFECTS: return password
    public String getPassword(){
        return this.password;
    }

//    // EFFECTS: return true if the given email is as same as the email for the account
//    public boolean checkValidEmail(String toBeChecked){
//        if(toBeChecked.equals(this.email)){
//            return true;
//        }
//        return false;
//    }
//
//    // EFFECTS: return true if the given password is as same as the password for the account
//    public boolean checkValidPassword(String toBeChecked){
//        if(toBeChecked.equals(this.password)){
//            return true;
//        }
//        return false;
//    }

    // EFFECTS: return the string that describe the Account
    public String toString(){
        String message = email + " " + password;
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, password);
    }
}
