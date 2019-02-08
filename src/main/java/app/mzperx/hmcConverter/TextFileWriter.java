package app.mzperx.hmcConverter;

import app.mzperx.matrices.ArchEdContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {
    private String outputLocation;
    List<ArchEdContext> contexts;

    public TextFileWriter(String outputLocation){
        this. outputLocation = outputLocation;
    }

    public void setContexts(List<ArchEdContext> contexts) {
        this.contexts = contexts;
    }

    public void contextListToFile(List<ArchEdContext> listOfContexts){
        // write the content in file
        if (listOfContexts!=null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.outputLocation))) {
                for (ArchEdContext context : listOfContexts) {
                    bufferedWriter.write(context.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Exiting...");
        }
    }
}
