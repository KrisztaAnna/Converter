package app.mzperx.hmcConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectXMLWriter {

   public static void writeProjectXML(){
       try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/krisztinabaranyai/Desktop/project.xml"))) {
           bufferedWriter.write("<?xml version=\"1.0\" ?><ProjectProperties Name=\"\" Description=\"\" ExcavationSite=\"\"></ProjectProperties>");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


}
