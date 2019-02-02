package app.mzperx.hmcConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {
    private String outputLocation;

    public TextFileWriter(String outputLocation){
        this. outputLocation = outputLocation;
    }

    public void writeParsedContent(String content){
        // write the content in file
        if (content!=null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.outputLocation))) {
                bufferedWriter.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Exiting...");
        }
    }
}
