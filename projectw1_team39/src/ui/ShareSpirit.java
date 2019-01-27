package ui;

import exceptionTool.NoAuthorityException;
import exceptionTool.NoLoggedInUserException;
import exceptionTool.OutOfListException;
import exceptionTool.WrongInputException;
import model.Account;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;


public class ShareSpirit extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 800;
    private final String ADD_BOOK = "Add a book";
    private final String DELETE_BOOK = "Delete a book";
    private final String CHECK_LIST = "Check book list";
    private final String CHECK_REQUEST_BOX = "Check request box";
    private final String CHECK_RESPONSE_BOX = "Check response box";
    private final String SEND_REQUEST = "Send a request";
    private final String WRITE_RESPONSE = "Write a response";
    private final String EMPTY_REQUEST_BOX = "Empty request box";
    private final String EMPTY_RESPONSE_BOX = "Empty response box";

    private ControlPane cp;

    private JPanel totalPanel;
    private JPanel welcomePanel;
    private JPanel controlPanel;
    private JLabel header;
    private JPanel welcomeOptionPanel;
    private JPanel signUpSignInPanel;
    private JTextField emailInput;
    private JTextField passwordInput;
    private JButton signUpButton;
    private JButton signInButton;
    private JLabel warningMsgWelcomePanel;

    private JPanel controlOptionsPanel;
    private JTextArea display;
    private JLabel warningMsgControlPanel;
    private JButton b10;
    private JPanel addBookInputPanel;
    private JPanel indexInputPanel;
    private JComboBox<String> options;
    private JPanel comboPanel;
    private JLabel reminder;
    private JPanel buttonsPanel;
    private JTextField bookNameInput;
    private JTextField authorInput;
    private JTextField editionInput;
    private JTextField priceInput;
    private JTextField indexInput;
    private JTextField responseInput;
    private JPanel inputPanel;
    private JScrollPane displayScollBar;

    public ShareSpirit(){
        //create frame
        super("Share Spirit");
        setTitle("Share Spirit");
        addWindowListener(new MyWindowListener());
        //setResizable(false);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);

        cp = new ControlPane();

        createWelcomePanel();
        createControlPanel();
        setActionListener();

        totalPanel = new JPanel(new CardLayout());
        totalPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        totalPanel.add(welcomePanel, "welcome");
        totalPanel.add(controlPanel, "control");

        //add panels to frame
        add(totalPanel,BorderLayout.CENTER);
        CardLayout cl = (CardLayout) totalPanel.getLayout();
        cl.show(totalPanel,"welcome");
        setVisible(true);
    }

    //get from google
    class MyWindowListener implements WindowListener {

        public void windowClosing(WindowEvent arg0) {
            try {
                cp.save();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                System.exit(0);
            }
        }

        public void windowOpened(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowActivated(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}

    }

    public void setActionListener(){
        signUpButton.addActionListener(E->{signUp();});
        signInButton.addActionListener(E->{signIn();});
        b10.addActionListener(E->{signOut();});
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                String selected = (String) combo.getSelectedItem();

                if (selected.equals(ADD_BOOK)) {
                    addNewBook();
                } else if (selected.equals(DELETE_BOOK)) {
                    deleteBook();
                }else if( selected.equals(CHECK_LIST)){
                    checkList();
                }else if(selected.equals(CHECK_REQUEST_BOX)){
                    checkRequestBox();
                }else if(selected.equals(CHECK_RESPONSE_BOX)){
                    checkResponseBox();
                }else if(selected.equals(SEND_REQUEST)){
                    sendRequest();
                }else if(selected.equals(WRITE_RESPONSE)){
                    writeResponse();
                }else if(selected.equals(EMPTY_REQUEST_BOX)){
                    emptyRequestBox();
                }else if(selected.equals(EMPTY_RESPONSE_BOX)){
                    emptyResponseBox();
                }
            }
        });
    }

    public void createControlPanel(){
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        controlOptionsPanel = new JPanel(new BorderLayout());
        controlOptionsPanel.setPreferredSize(new Dimension(1000,350));
        b10 = new JButton(new ImageIcon("signOut.png"));
        b10.setBounds(0,0,200,100);
        b10.setFont(new Font("Times New Roman", Font.BOLD, 15));
        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.setPreferredSize(new Dimension(800, 200));
        b10.setPreferredSize(new Dimension(175,100));
        JPanel b10Panel = new JPanel(new BorderLayout());
        b10Panel.setPreferredSize(new Dimension(175,200));
        JLabel n = new JLabel("");
        n.setPreferredSize(new Dimension(175,75));
        JLabel s = new JLabel("");
        s.setPreferredSize(new Dimension(175,75));
        b10Panel.add(n,BorderLayout.NORTH);
        b10Panel.add(s,BorderLayout.SOUTH);
        b10Panel.add(b10,BorderLayout.CENTER);

        buttonsPanel.add(b10Panel,BorderLayout.WEST);
        reminder = new JLabel("<html>Get an option!<br>Type book name, author, edition, price to add a book" +
                "<br>Type index to delete book and send a request<br>Type index and T/F to write a response</html>",SwingConstants.CENTER);
        reminder.setPreferredSize(new Dimension(550,200));
        reminder.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JLabel pic = new JLabel(new ImageIcon("bookPic.jpg"));
        pic.setPreferredSize(new Dimension(250,200));
        buttonsPanel.add(reminder,BorderLayout.CENTER);
        buttonsPanel.add(pic,BorderLayout.EAST);
        controlOptionsPanel.add(buttonsPanel,BorderLayout.NORTH);

        bookNameInput = new JTextField("Enter book name");
        bookNameInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        bookNameInput.setBackground(new Color(249,165,189));
        authorInput = new JTextField("Enter author");
        authorInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        authorInput.setBackground(new Color(249,165,189));
        editionInput = new JTextField("Enter edition");
        editionInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        editionInput.setBackground(new Color(249,165,189));
        priceInput = new JTextField("Enter price");
        priceInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        priceInput.setBackground(new Color(249,165,189));
        addBookInputPanel = new JPanel(new GridLayout(1,4));
        addBookInputPanel.add(bookNameInput);
        addBookInputPanel.add(authorInput);
        addBookInputPanel.add(editionInput);
        addBookInputPanel.add(priceInput);
        indexInput = new JTextField("Enter index");
        indexInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        indexInput.setBackground(new Color(249,165,189));
        responseInput = new JTextField("Enter opinion(true, false)");
        responseInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        responseInput.setBackground(new Color(249,165,189));
        indexInputPanel = new JPanel(new GridLayout(1,2));
        indexInputPanel.add(indexInput);
        indexInputPanel.add(responseInput);
        inputPanel = new JPanel(new GridLayout(2,1));
        inputPanel.add(addBookInputPanel);
        inputPanel.add(indexInputPanel);
        comboPanel = new JPanel(new GridLayout(1,2));
        comboPanel.add(inputPanel);
        options = new JComboBox<String>();
        options.addItem(CHECK_LIST);
        options.addItem(ADD_BOOK);
        options.addItem(DELETE_BOOK);
        options.addItem(CHECK_REQUEST_BOX);
        options.addItem(CHECK_RESPONSE_BOX);
        options.addItem(SEND_REQUEST);
        options.addItem(WRITE_RESPONSE);
        options.addItem(EMPTY_REQUEST_BOX);
        options.addItem(EMPTY_RESPONSE_BOX);
        options.setFont(new Font("Times New Roman", Font.BOLD, 20));
        comboPanel.add(options);
        comboPanel.setPreferredSize(new Dimension(800,150));
        controlOptionsPanel.add(comboPanel,BorderLayout.SOUTH);

        controlPanel.add(controlOptionsPanel,BorderLayout.NORTH);

        display = new JTextArea();
        display.setPreferredSize(new Dimension(1000,350));
        display.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        display.setBackground(Color.PINK);
        displayScollBar = new JScrollPane(display,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        controlPanel.add(displayScollBar,BorderLayout.CENTER);

        warningMsgControlPanel = new JLabel("Welcome",SwingConstants.CENTER);
        warningMsgControlPanel.setFont(new Font("Times New Roman", Font.PLAIN, 50) );
        warningMsgControlPanel.setPreferredSize(new Dimension(1000,100));
        controlPanel.add(warningMsgControlPanel,BorderLayout.SOUTH);

    }

    public void initialControlPanel(){
        bookNameInput.setText("Enter book name");
        editionInput.setText("Enter edition");
        authorInput.setText("Enter author");
        priceInput.setText("Enter price");
        indexInput.setText("Enter index");
        responseInput.setText("Enter opinion(true, false)");
    }

    public void createWelcomePanel(){
        //create welcome panel
        welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);

        //create header text JLabel to add to the north of welcomePanel
        header = new JLabel(new ImageIcon("bookI.png"));
        header.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT*2/3));
        //header.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        welcomePanel.add(header,BorderLayout.NORTH);

        //create optionArea to the center of the welcomePanel
        welcomeOptionPanel = new JPanel(new BorderLayout());
        welcomeOptionPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT/6));

        emailInput = new JTextField("joy");
        emailInput.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        emailInput.setBackground(new Color(160,216,254));
        emailInput.setPreferredSize(new Dimension(400,PANEL_HEIGHT/6));

        passwordInput = new JTextField("123");
        passwordInput.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        passwordInput.setBackground(new Color(160,216,254));
        passwordInput.setPreferredSize(new Dimension(400,PANEL_HEIGHT/6));

        signUpSignInPanel = new JPanel(new GridLayout(2,1));
        signUpSignInPanel.setPreferredSize(new Dimension(150,PANEL_HEIGHT/6));
        signUpButton = new JButton(new ImageIcon("signUp.png"));
        signUpButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        signInButton = new JButton(new ImageIcon("signIn.png"));
        signInButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        signUpSignInPanel.add(signUpButton);
        signUpSignInPanel.add(signInButton);
        welcomeOptionPanel.add(emailInput,BorderLayout.WEST);
        welcomeOptionPanel.add(passwordInput,BorderLayout.CENTER);
        welcomeOptionPanel.add(signUpSignInPanel,BorderLayout.EAST);

        welcomePanel.add(welcomeOptionPanel,BorderLayout.CENTER);

        //create message area to the south area of the welcomePanel
        warningMsgWelcomePanel = new JLabel("Please choose sign up or sign in", SwingConstants.CENTER);
        warningMsgWelcomePanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT/6));
        warningMsgWelcomePanel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        welcomePanel.add(warningMsgWelcomePanel,BorderLayout.SOUTH);

    }

//    public void run(){
//        //welcomePage();
//    }
    private static void music(String path){
        AudioStream BGM;
        try{
            BGM = new AudioStream(new FileInputStream(path));
            AudioPlayer.player.start(BGM);
        }catch(IOException error){
            System.out.print("file not found");
        }
    }

    private void signOut(){
        cp.unsetUserInProgress();
        CardLayout cl = (CardLayout) totalPanel.getLayout();
        cl.show(totalPanel, "welcome");
    }

    private void signUp() {
        this.warningMsgWelcomePanel.setText("Please choose sign up or sign in");
        boolean signedIn = cp.isSignedIn();
        if(!signedIn) {
            String email = emailInput.getText();
            String password = passwordInput.getText();

            Account newUser = new Account(email, password);
            boolean addAccountSuccess = cp.addAccount(email,password);
            if(addAccountSuccess) {
                music("success.wav");
                this.warningMsgWelcomePanel.setText("Create " + newUser.toString() + " successfully.");
            }
            else {
                music("fail.wav");
                this.warningMsgWelcomePanel.setText("Cannot create " + newUser.toString());
            }
        }
        else{
            music("fail.wav");
            this.warningMsgWelcomePanel.setText("Someone is signed in");
        }
    }

    private void signIn() {
        display.setText("");
        warningMsgWelcomePanel.setText("Please choose sign up or sign in");
        String userName = emailInput.getText();
        String password = passwordInput.getText();
        boolean setUserInProgressSuccess = cp.setUserInProgress(userName, password);
        if (!setUserInProgressSuccess) {
            music("fail.wav");
            this.warningMsgWelcomePanel.setText("There is no such user! Try again.");
        } else {
            initialControlPanel();
            display.setText("");
            music("success.wav");
            CardLayout cl = (CardLayout) totalPanel.getLayout();
            cl.show(totalPanel, "control");
            warningMsgControlPanel.setText("Welcome " + userName);
        }
    }

    private  void checkList(){
        try {
            this.display.setText(cp.checkList());
            warningMsgControlPanel.setText("Here is the book list");
            initialControlPanel();
         } catch (NoLoggedInUserException e) {
            music("fail.wav");
             this.warningMsgControlPanel.setText("No user logged in!");
         }
    }

    private void addNewBook(){
        try {
            String bookN = bookNameInput.getText();
            String edition = editionInput.getText();
            String authorN = authorInput.getText();
            double p = Double.parseDouble(priceInput.getText());
            if (cp.addNewBook(bookN, edition, authorN, p)) {
                music("success.wav");
                warningMsgControlPanel.setText("Add book successfully");
            } else {
                music("fail.wav");
                warningMsgControlPanel.setText("Add duplicated book failed. Try again!");
            }
            initialControlPanel();
        }catch(NoLoggedInUserException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch(NumberFormatException e){
            music("fail.wav");
            warningMsgControlPanel.setText("Please enter number for price!");
        }
    }

    private void deleteBook(){
        try {
            int index = Integer.parseInt(indexInput.getText());
            cp.deleteBook(index);
            music("success.wav");
            warningMsgControlPanel.setText("Delete the book successfully");
            initialControlPanel();
        } catch (NoLoggedInUserException e) {
            music("fail.wav");
           warningMsgControlPanel.setText(e.getMessage());
        } catch (OutOfListException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        } catch (NoAuthorityException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch(NumberFormatException e){
            music("fail.wav");
            warningMsgControlPanel.setText("Please enter number!");
        }
    }

    private void sendRequest(){
        try {
            int index = Integer.parseInt(indexInput.getText());
            //TODO
            cp.sendRequest(index);
            music("success.wav");
            warningMsgControlPanel.setText("Send request successfully");
            initialControlPanel();
        } catch(NoLoggedInUserException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch(WrongInputException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch(NumberFormatException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }

    private void checkRequestBox(){
        try {
            display.setText(cp.checkRequestBox());
            warningMsgControlPanel.setText("Your request box");
        } catch (NoLoggedInUserException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }

    private void writeResponse(){
        try {
            int index = Integer.parseInt(indexInput.getText());
            String opinion = responseInput.getText();
            if (opinion.equals("true")) {
                music("success.wav");
                cp.sendResponse(index, true);
                warningMsgControlPanel.setText("Write response successfully");
            } else if (opinion.equals("false")) {
                music("success.wav");
                cp.sendResponse(index, false);
                warningMsgControlPanel.setText("Write response successfully");
            } else {
                music("fail.wav");
                warningMsgControlPanel.setText("Invalid opinion.");
            }
            initialControlPanel();
        }catch(NoLoggedInUserException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch(WrongInputException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }catch (NumberFormatException e){
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }

    private void checkResponseBox(){
        try {
            display.setText(cp.checkResponseBox());
            warningMsgControlPanel.setText("Your response box");
        } catch (NoLoggedInUserException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }

    private void emptyRequestBox(){
        try {
            cp.emptyRequestBox();
            music("empty.wav");
            warningMsgControlPanel.setText("Emptied request box");
        } catch (NoLoggedInUserException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }

    private void emptyResponseBox(){
        try {
            cp.emptyResponseBox();
            music("empty.wav");
            warningMsgControlPanel.setText("Emptied response box");
        } catch (NoLoggedInUserException e) {
            music("fail.wav");
            warningMsgControlPanel.setText(e.getMessage());
        }
    }
//    private void processAccount(){
//        System.out.println("Logged in. Welcome!");
//        System.out.println("The user in progress is  " + cp.getUserInProgress().toString() + ".");
//        while (true) {
//            try {
//                System.out.print("1 Check book list\n");
//                System.out.print("2 Add new book to sell\n" + "3 Delete a book\n" + "4 Send a request\n" + "5 Check request box\n");
//                System.out.print("6 Write a response\n" + "7 Check response box\n" + "8 Empty request box\n" + "9 Empty response box\n");
//                System.out.println("10 Sign out");
//                System.out.println("Please enter your choice: ");
//                int choice = Integer.parseInt(sc.nextLine());
//                if (choice == 1) {
//                    //TODO
//                    System.out.println(cp.checkList());
//                }
//                else if (choice == 2) {
//                    System.out.println("Please enter the book name: ");
//                    String bookN = sc.nextLine();
//                    System.out.println("Please enter the edition number: ");
//                    String edition = sc.nextLine();
//                    System.out.println("Please enter the author name: ");
//                    String authorN = sc.nextLine();
//                    System.out.println("Please enter the price: ");
//                    double p = Double.parseDouble(sc.nextLine());
//                    //TODO
//                    if (cp.addNewBook(bookN, edition, authorN, p)) {
//                        System.out.println("Add book successfully");
//                    }
//                    else {
//                        System.out.println("Add book failed. Try again!");
//                    }
//                }
//                else if (choice == 3) {
//                    System.out.println("Please enter the number of book (start from 0): ");
//                    int index = Integer.parseInt(sc.nextLine());
//                    cp.deleteBook(index);
//                    System.out.println("Delete the book successfully");
//                }
//                else if (choice == 4) {
//                    System.out.println("Please enter the number of book (start from 0): ");
//                    int index = Integer.parseInt(sc.nextLine());
//                    //TODO
//                    cp.sendRequest(index);
//                    System.out.println("Send request successfully");
//                }
//                else if (choice == 5) {
//                    System.out.println(cp.checkRequestBox());
//                }
//                else if (choice == 6) {
//                    System.out.println("Please enter the number of request you want to reply (start from 0): ");
//                    int index = Integer.parseInt(sc.nextLine());
//                    System.out.println("Please enter your opinion about selling the book to this person(true for yes, false for no): ");
//                    String opinion = sc.nextLine();
//                    if (opinion.equals("true")) {
//                        cp.sendResponse(index, true);
//                        System.out.println("Write response successfully");
//                    }
//                    else if (opinion.equals("false")) {
//                        cp.sendResponse(index, false);
//                        System.out.println("Write response successfully");
//                    }
//                    else {
//                        System.out.println("Invalid opinion.");
//                    }
//                }
//                else if (choice == 7) {
//                    System.out.println(cp.checkResponseBox());
//                }
//                else if (choice == 8) {
//                    cp.emptyRequestBox();
//                }
//                else if (choice == 9) {
//                    cp.emptyResponseBox();
//                }
//                else if (choice == 10) {
//                    System.out.println("Sign out......");
//                    //TODO
//                    cp.unsetUserInProgress();
//                    break;
//                }
//                else {
//                    throw new InvalidChoiceException("The choice is invalid!");
//                }
//            } catch(NoLoggedInUserException e){
//                System.out.println(e.getMessage());
//            }
//            catch(WrongInputException e){
//                System.out.println(e.getMessage());
//            }
//            catch(NoAuthorityException e){
//                System.out.println(e.getMessage());
//            }
//        }//end while
//    }//end method
}
