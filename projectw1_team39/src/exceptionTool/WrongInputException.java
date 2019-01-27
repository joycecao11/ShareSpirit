package exceptionTool;

import java.io.IOException;

public abstract class WrongInputException extends IOException{
    public WrongInputException(String msg){
        super(msg);
    }
}
