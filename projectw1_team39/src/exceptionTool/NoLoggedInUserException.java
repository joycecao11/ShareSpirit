package exceptionTool;

import java.io.IOException;

public class NoLoggedInUserException extends IOException{
    public NoLoggedInUserException(String msg){
        super(msg);
    }
}
