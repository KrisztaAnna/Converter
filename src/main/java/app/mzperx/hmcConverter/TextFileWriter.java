package app.mzperx.hmcConverter;

import app.mzperx.exception.EmptyContextListException;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {
    private static final Logger logger = LoggerFactory.getLogger(ArchEdContext.class);
    private String outputLocation;

    public TextFileWriter(String outputLocation){
        this. outputLocation = outputLocation;
    }

    public void contextListToFile(List<ArchEdContext> listOfContexts) throws EmptyContextListException, IOException{
        // write the content in file
        if (listOfContexts!=null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.outputLocation))) {
                logger.info("Writing output file...");
                for (ArchEdContext context : listOfContexts) {
                    bufferedWriter.write(context.toString());
                }
                logger.info("Conversion finished. Output file can be found at the following location: " + this.outputLocation + "\n");
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }else if (listOfContexts.size() == 0){
            EmptyContextListException e = new EmptyContextListException();
            logger.error(e.getMessage(), e);
        }
    }
}
