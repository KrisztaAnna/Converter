package app.mzperx.hmcConverter;


import java.io.*;
import java.util.Scanner;

public class TextFileLoader {

    private String inputLocation;

    public TextFileLoader(String inputLocation){
        this.inputLocation = inputLocation;
    }

    private File getInputFile(){
        return new File(inputLocation);
    }

    public String getContent() {
        try {
            Scanner scanner = new Scanner(getInputFile());
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            return content;

        } catch (FileNotFoundException e1) {
            System.out.println("Input file is missing or not in the correct format.");
        }
        return null;
    }

}
