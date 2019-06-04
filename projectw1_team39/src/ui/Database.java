package ui;

import interfaceTool.Loadable;
import interfaceTool.Saveable;
import model.Account;
import model.Book;
import model.Request;
import model.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database implements Loadable, Saveable{
    private List<String> accountLines;
    private List<String> bookLines;
    private List<String> requestLines;
    private List<String> responseLines;
    private PrintWriter accountWriter;
    private PrintWriter bookWriter;
    private PrintWriter requestWriter;
    private PrintWriter responseWriter;

    private ControlPane cp;

    public Database(ControlPane cp){
        accountLines = null;
        bookLines = null;
        requestLines = null;
        responseLines = null;
        accountWriter = null;
        bookWriter = null;
        requestWriter = null;
        responseWriter = null;
        this.cp = cp;
    }
    @Override
    public void load() throws IOException {
        System.out.println("I am loading");
        accountLines = Files.readAllLines(Paths.get("account.txt"));;
        bookLines = Files.readAllLines(Paths.get("book.txt"));;
        requestLines = Files.readAllLines(Paths.get("request.txt"));;
        responseLines = Files.readAllLines(Paths.get("response.txt"));;
        loadAccount();
        //System.out.println(listOfAcc.toString());
        loadBook();
        //System.out.println(listOfBook.toString());
        loadRequest();
        loadResponse();
    }

    private void loadAccount(){
        for (String line : accountLines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            cp.getListOfAcc().addAccount(partsOfLine.get(0),partsOfLine.get(1));
        }
    }

    private void loadBook(){
        if(!bookLines.isEmpty()){
            for (String line : bookLines){
                ArrayList<String> partsOfLine = splitOnSpace(line);
                cp.getListOfBook().addNewBook(partsOfLine.get(0),partsOfLine.get(1),partsOfLine.get(2),Double.parseDouble(partsOfLine.get(3)),partsOfLine.get(4));
            }
        }else{
            //ArrayList<String> partsOfLine = splitOnSpace(line);
        }
    }

    public void sendRequest(String sender, String bookN,String edition,String authorN,String ownerN){
        Book b = cp.getListOfBook().getBook(bookN,edition,authorN,ownerN);
        //System.out.println("Sending request...........");
        Request r = new Request(b,sender);
        cp.getListOfAcc().getAccount(b.getAccount()).receiveRequest(r);
    }

    private void loadRequest(){
        for (String line : requestLines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            sendRequest(partsOfLine.get(0),partsOfLine.get(1),partsOfLine.get(2),partsOfLine.get(3),partsOfLine.get(5));
        }
    }

    private void sendResponse(String receiver, String bookN,String edition,String authorN,String sender,boolean agree){
        Book b = cp.getListOfBook().getBook(bookN,edition,authorN,sender);
        //System.out.println("Sending response...........");
        Response r = new Response(b,sender,agree);
        cp.getListOfAcc().getAccount(receiver).receiveResponse(r);
    }

    private void loadResponse(){
        for (String line : responseLines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if(partsOfLine.get(6).equals("true")) {
                sendResponse(partsOfLine.get(0),partsOfLine.get(1),partsOfLine.get(2),partsOfLine.get(3),partsOfLine.get(5),true);
            }
            else{
                sendResponse(partsOfLine.get(0),partsOfLine.get(1),partsOfLine.get(2),partsOfLine.get(3),partsOfLine.get(5),false);
            }
        }
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    @Override
    public void save() throws IOException{
        System.out.println("I am saving.........");
        saveAccount();
        saveBook();
        accountWriter = new PrintWriter("account.txt","UTF-8");
        bookWriter = new PrintWriter("book.txt","UTF-8");
        requestWriter = new PrintWriter("request.txt","UTF-8");
        responseWriter = new PrintWriter("response.txt","UTF-8");
        for (String line : accountLines){
            accountWriter.println(line);
        }
        accountWriter.close();
        for (String line : bookLines){
            bookWriter.println(line);
        }
        bookWriter.close();
        for (String line : requestLines){
            requestWriter.println(line);
        }
        requestWriter.close();
        for (String line : responseLines){
            responseWriter.println(line);
        }
        responseWriter.close();
    }

    private void saveAccount(){
        ArrayList<Account> acc = cp.getListOfAcc().getAccountList();
        accountLines.clear();
        for (Account a : acc){
            accountLines.add(a.toString());
        }
    }

    private void saveBook(){
        ArrayList<Book> bookL = cp.getListOfBook().getBookList();
        bookLines.clear();
        for (Book b : bookL){
            bookLines.add(b.toString());
        }
    }

    public List<String> getAccountLines() {
        return accountLines;
    }

    public List<String> getBookLines() {
        return bookLines;
    }

    public List<String> getRequestLines() {
        return requestLines;
    }

    public List<String> getResponseLines() {
        return responseLines;
    }
}
