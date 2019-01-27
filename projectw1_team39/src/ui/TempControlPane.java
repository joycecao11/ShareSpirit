//package ui;
//
//import model.Account;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class TempControlPane {
//    private ArrayList<Account> accountList;
//    public Scanner sc;
//    private Account userInProgress;
//
//    public TempControlPane(){
//        accountList = new ArrayList<>();
//        sc = new Scanner(System.in);
//        userInProgress = null;
//    }
//
//    public void run(){
//        welcomePage();
//    }
//
//    private void welcomePage(){
//        while(true) {
//            System.out.println("-->This is the welcome page.\nWelcome and start your journey to learn better and share with other other lovely people! :)");
//            System.out.println("1 Sign up\n" + "2 Sign in\n" + "3 Log out");
//            System.out.println("Please enter your choice: ");
//            int choice = Integer.parseInt(sc.nextLine());
//            if (choice == 3) {
//                System.out.println("Quiting......");
//                quit();
//            }
//            processCommand(choice);
//        }
//    }
//
//    private void processCommand(int cmd){
//        if(cmd==1){
//            signUp();
//        }
//        else if(cmd==2){
//            signIn();
//        }
//        else if(cmd==3){
//            quit();
//        }
//        else{
//            System.out.println("Invalid choice. Try again.");
//        }
//    }
//
//    private void signUp() {
//        System.out.println("-->This is the sign up page.");
//        if(userInProgress==null) {
//            System.out.println("Please enter your email: ");
//            String email = sc.nextLine();
//            System.out.println("Please enter your password: ");
//            String password = sc.nextLine();
//            Account newUser = new Account(email, password);
//            this.accountList.add(newUser);
//            System.out.println("Create " + newUser.toString() + " successfully.");
//        }
//        else{
//            System.out.println("You are signed in.");
//        }
//    }
//
//    private void signIn() {
//        System.out.println("-->This is the sign in page.");
//        if(userInProgress==null) {
//            //get user info and search in database
//            userInProgress = null;
//            while (userInProgress == null) {
//                System.out.println("Would you like to log in? Y/N ");
//                String choice = sc.nextLine();
//                if(choice.equals("Y")) {
//                    System.out.println("Please enter the email: ");
//                    String tempEmail = sc.nextLine();
//                    System.out.println("Please enter the password: ");
//                    String tempPIN = sc.nextLine();
//                    for (int i = 0; i < this.accountList.size(); i++) {
//                        if (this.accountList.get(i).checkValidEmail(tempEmail) && this.accountList.get(i).checkValidPassword(tempPIN)) {
//                            userInProgress = this.accountList.get(i);
//                        }
//                    }
//                    if (userInProgress == null) {
//                        System.out.println("There is no such user! Try again.");
//                    }
//                }
//                else if (choice.equals("N")){
//                    break;
//                }
//                else{
//                    System.out.println("Invalid choice. Try again.");
//                }
//            }
//            if(userInProgress!=null) {
//                processAccount();
//            }
//        }
//        else{
//            System.out.println("You are  signed in.");
//        }
//    }
//
//    private void quit() {
//        System.exit(0);
//    }
//
//    private void processAccount(){
//        System.out.println("Logged in. Welcome!");
//        System.out.println("The user in progress is  " + userInProgress.toString() + ".");
//        while(true) {
//            System.out.print("1 Check list\n" + "2 Add new course\n" );
//            System.out.print("3 Delete a course\n" + "4 Add new book to sell\n" + "5 Delete a book\n" + "6 Sign out\n");
//            System.out.println("Please enter your choice: ");
//            int choice = Integer.parseInt(sc.nextLine());
//            if (choice == 1) {
//                System.out.println(userInProgress.getList());
//            }
//            else if(choice == 2){
//                System.out.println("Please enter the course name: ");
//                String courseN = sc.nextLine();
//                userInProgress.addNewCourse(courseN);
//            }
//            else if(choice == 3){
//                System.out.println("Please enter the course name: ");
//                String courseN = sc.nextLine();
//                userInProgress.deleteCourse(courseN);
//            }
//            else if(choice == 4){
//                System.out.println("Please enter the course name: ");
//                String courseN = sc.nextLine();
//                System.out.println("Please enter the book name: ");
//                String bookN = sc.nextLine();
//                System.out.println("Please enter the edition number: ");
//                int edition = Integer.parseInt(sc.nextLine());
//                System.out.println("Please enter the author name: ");
//                String authorN = sc.nextLine();
//                System.out.println("Please enter the price: ");
//                double p = Double.parseDouble(sc.nextLine());
//                userInProgress.addNewBook(courseN, bookN,edition,authorN,p);
//            }
//            else if(choice == 5){
//                System.out.println("Please enter the course name: ");
//                String courseN = sc.nextLine();
//                System.out.println("Please enter the book name: ");
//                String bookN = sc.nextLine();
//                System.out.println("Please enter the edition number: ");
//                int edition = Integer.parseInt(sc.nextLine());
//                System.out.println("Please enter the author name: ");
//                String authorN = sc.nextLine();
//                userInProgress.deleteBook(courseN, bookN,edition,authorN);
//            }
//            else if( choice == 6){
//                System.out.println("Sign out......");
//                userInProgress = null;
//                break;
//            }
//            else{
//                System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//}