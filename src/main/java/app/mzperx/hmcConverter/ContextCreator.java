package app.mzperx.hmcConverter;

import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextCreator {

    private File inputFile;

    ContextCreator(File inputFile){
        this.inputFile = inputFile;
    }

    private String getAllContent() {
        try {
            Scanner scanner = new Scanner(inputFile);
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            return content;

        } catch (FileNotFoundException e1) {
            System.out.println("Input file is missing or not in the correct format.");
        }
        return null;
    }

     void parseContent(){
        try {
            String content = getAllContent();
            getContextNumber(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getContextNumber(String content) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String name = matcher.group(0);
            String contextNumber = name.replaceAll("[^0-9]","");
            ArchEdContext context = new ArchEdContext(contextNumber);
            System.out.println(context.getName());

        }
    }


//    private ArchEdContext createContext(String content){
//        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
//        Matcher matcher = pattern.matcher(content);
//
//        while (matcher.find()) {
//            String name = matcher.group(0);
//            String contextNumber = name.replaceAll("[^0-9]","");
//            ArchEdContext context = new ArchEdContext(contextNumber);
//        }
//    }


    //    private String getContent(){
//        try {
//            Scanner scanner = new Scanner(getInputFile());
//            String content = scanner.useDelimiter("\\n.\\d{5}.").next();
//            scanner.close();
//            return content;
//
//        } catch (FileNotFoundException e1) {
//            System.out.println("Input file is missing or not in the correct format.");
//        }
//        return null;
//    }
}
