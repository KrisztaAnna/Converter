package app.mzperx.hmcConverter;

import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectXMLWriter {
    private static final Logger logger = LoggerFactory.getLogger(ProjectXMLWriter.class);
    static String text ="<?xml version=\"1.0\" ?><ProjectProperties Name=\"\" Description=\"\" ExcavationSite=\"\"></ProjectProperties>";

   public static void writeProjectXML(){
       try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/krisztinabaranyai/Desktop/project.xml"))) {
           bufferedWriter.write(text);
            logger.info("Writing project.xml ");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


}
