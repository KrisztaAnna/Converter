package app.mzperx.hmcConverter;

import app.mzperx.exception.EmptyContextListException;
import app.mzperx.hmcConverter.model.ArchEdContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {
    private String outputLocation;

    public TextFileWriter(String outputLocation){
        this. outputLocation = outputLocation;
    }

    public void contextListToFile(List<ArchEdContext> listOfContexts) throws EmptyContextListException, IOException {
        // write the content in file
        if (listOfContexts!=null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.outputLocation))) {
                for (ArchEdContext context : listOfContexts) {
                    bufferedWriter.write(context.toString());
                }
            }
        } if (listOfContexts.size() == 0){
            throw new EmptyContextListException();
        }
    }
}
