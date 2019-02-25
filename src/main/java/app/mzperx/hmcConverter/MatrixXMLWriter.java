package app.mzperx.hmcConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixXMLWriter {
    private static final Logger logger = LoggerFactory.getLogger(MatrixXMLWriter.class);
    static MatrixXMLBuilder matrixXMLBuilder;

    public static void writeMatrixXML(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/krisztinabaranyai/Desktop/matrix.xml"))) {
            matrixXMLBuilder = new MatrixXMLBuilder();
            bufferedWriter.write(matrixXMLBuilder.buildGraph());
            logger.info("Writing matrix.xml ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
