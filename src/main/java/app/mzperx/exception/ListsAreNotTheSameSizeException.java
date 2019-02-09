package app.mzperx.exception;

public class ListsAreNotTheSameSizeException extends Exception{
    @Override
    public String getMessage() {
        return "Number of contexts and number of descriptions are not equal! \n" +
                "Exiting...";
    }
}
