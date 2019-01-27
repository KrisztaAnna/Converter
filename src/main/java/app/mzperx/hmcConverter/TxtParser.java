package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;

import java.io.*;
import java.util.Scanner;

public class TxtParser implements Converter {

    private String inputLocation;
    private String outputLocation;

    public TxtParser(String inputLocation, String outputLocation){
        this.inputLocation = inputLocation;
        this.outputLocation = outputLocation;
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
            System.out.println("Input file is missing.");
        }
        return null;
    }

    public void writeContent(){
        String content = getContent();
        // write the content in file
        if (content!=null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputLocation))) {
                bufferedWriter.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Exiting...");
        }
    }
}
