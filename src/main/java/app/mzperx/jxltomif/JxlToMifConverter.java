package app.mzperx.jxltomif;

import app.mzperx.converter.Converter;

import java.io.*;
import java.util.Scanner;

public class JxlToMifConverter implements Converter {

    private File inputFile;
    private String outputLocation;


    public JxlToMifConverter(File inputFile, String outputLocation){
        this.inputFile = inputFile;
        this.outputLocation = outputLocation;

    }

    public String getContent() {
        try {
            Scanner scanner = new Scanner(inputFile);
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
