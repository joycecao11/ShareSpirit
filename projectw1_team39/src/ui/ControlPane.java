package ui;

import abstractTool.MailingItem;
import exceptionTool.NoAuthorityException;
import exceptionTool.NoLoggedInUserException;
import exceptionTool.OutOfListException;
import model.*;

import java.io.IOException;
import java.util.*;

public class ControlPane{

    private Account userInProgress;
    private AccountList listOfAcc;
    private BookList listOfBook;
    private Database db;

    public ControlPane(){
        listOfAcc = new AccountList();
        listOfBook = new BookList();
        userInProgress = null;
        db = new Database(this);
        try {
            db.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setObserver(ShareSpirit ss){
        listOfAcc.setObserver(ss);
    }

    public String checkRequestBox() throws NoLoggedInUserException {
        if(userInProgress != null) {
            return userInProgress.checkRequestBox();
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public String checkResponseBox() throws NoLoggedInUserException {
        if(userInProgress != null) {
            return userInProgress.checkResponseBox();
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public void emptyRequestBox() throws NoLoggedInUserException {
        if(userInProgress != null) {
            this.userInProgress.emptyRequestBox();
            emptyRequestLinesAndResponseLines(db.getRequestLines(),5);
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public void emptyRequestLinesAndResponseLines(List<String> target,int key) throws NoLoggedInUserException {
        if(userInProgress != null) {
            Iterator<String> itr = target.iterator();
            while(itr.hasNext()) {
                ArrayList<String> partsOfLine = Database.splitOnSpace(itr.next());
                if (userInProgress.getEmail().equals(partsOfLine.get(key))) {
                    itr.remove();
                }
            }
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public void emptyResponseBox() throws NoLoggedInUserException {
        if(userInProgress != null) {
            this.userInProgress.emptyResponseBox();
            emptyRequestLinesAndResponseLines(db.getResponseLines(),0);
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public boolean sendRequest(int index) throws NoLoggedInUserException, OutOfListException {
        if(userInProgress != null) {
            Book b = listOfBook.getBook(index);
            if (b == null) {
                return false;
            } else {
                db.getRequestLines().add(userInProgress.getEmail() + " " + b.toString());
                System.out.println("Sending request...........");
                Request r = new Request(b, userInProgress.getEmail());
                listOfAcc.getAccount(b.getAccount()).receiveRequest(r);
                return true;
            }
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public boolean sendResponse(int index,boolean agree) throws NoLoggedInUserException, OutOfListException {
        if(userInProgress != null) {
            MailingItem r = userInProgress.getRequest(index);
            if (agree) {
                db.getResponseLines().add(r.getSender() + " " + r.getBook().toString() + " " + "true");
                listOfBook.deleteBook(r.getBook());
            } else {
                db.getResponseLines().add(r.getSender() + " " + r.getBook().toString() + " " + "false");
            }
            System.out.println("Sending response...........");
            Response response = new Response(r.getBook(), r.getSender(), agree);
            listOfAcc.getAccount(r.getSender()).receiveResponse(response);
            return true;
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public boolean isSignedIn(){
        if(userInProgress == null){
            return false;
        }
        return true;
    }

    public boolean setUserInProgress(String email, String password){
        userInProgress = listOfAcc.getAccount(email,password);
        if(userInProgress == null){
            return false;
        }
        return true;
    }

    public Account getUserInProgress(){
        return userInProgress;
    }

    public boolean addAccount(String email, String password) {
            return listOfAcc.addAccount(email, password);
    }

    public String checkList() throws NoLoggedInUserException {
        if (userInProgress != null) {
            return listOfBook.toString();
        } else {
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public boolean addNewBook(String bookN, String edition, String authorN, double price) throws NoLoggedInUserException {
        if(userInProgress != null) {
            return listOfBook.addNewBook(bookN, edition, authorN, price, userInProgress.getEmail());
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public void deleteBook(int index) throws NoLoggedInUserException, OutOfListException, NoAuthorityException {
        if(userInProgress != null) {
            listOfBook.deleteBook(index, userInProgress.getEmail());
        }
        else{
            throw new NoLoggedInUserException("There is no logged in user!");
        }
    }

    public void unsetUserInProgress(){
        userInProgress = null;
    }

    public AccountList getListOfAcc() {
        return listOfAcc;
    }

    public BookList getListOfBook() {
        return listOfBook;
    }

    public void save() throws IOException {
        db.save();
    }

}
