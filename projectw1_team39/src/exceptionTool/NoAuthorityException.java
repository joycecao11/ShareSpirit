package exceptionTool;

public class NoAuthorityException extends Exception{
    public NoAuthorityException(String msg){
        super(msg);
    }
}
