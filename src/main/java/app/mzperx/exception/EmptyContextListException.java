package app.mzperx.exception;

public class EmptyContextListException extends Exception{

    @Override
    public String getMessage() {
        return "No context number was extracted from input file!";
    }
}
