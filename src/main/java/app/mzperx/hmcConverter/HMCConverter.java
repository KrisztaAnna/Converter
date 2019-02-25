package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;
import app.mzperx.exception.EmptyContextListException;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class HMCConverter implements Converter{
    private static final Logger logger = LoggerFactory.getLogger(ArchEdContext.class);
    private ContextExtractor contextExtractor;
    private TextFileWriter textFileWriter;
    private ArchEdContextToNodesAndEdges archEdContextToNodesAndEdges;

    public HMCConverter(String inputLocation, String outputLocation){
        this.contextExtractor = new ContextExtractor(new File(inputLocation));
        this.textFileWriter = new TextFileWriter(outputLocation);
    }

    public void convert(){
        logger.info("Conversion starts... ");
        archEdContextToNodesAndEdges = new ArchEdContextToNodesAndEdges();
        try {
            List<ArchEdContext> contexts = contextExtractor.txtToArchEdContextList();
            textFileWriter.contextListToFile(contexts);
            archEdContextToNodesAndEdges.createNodesandEdges();
            ProjectXMLWriter.writeProjectXML();
            MatrixXMLWriter.writeMatrixXML();
        }catch (EmptyContextListException e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getMessage(), e);
        }catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
