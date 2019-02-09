package app.mzperx.exception;

public class EmptyContextListException extends Exception{

    @Override
    public String getMessage() {
        return "Context list is empty!";
    }
}
