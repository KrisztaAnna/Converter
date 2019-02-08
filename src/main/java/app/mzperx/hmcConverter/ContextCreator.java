package app.mzperx.hmcConverter;

import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    private List<ArchEdContext> getListOfContexts(String content) throws FileNotFoundException {
        List<ArchEdContext> listOfContexts = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String name = matcher.group(0);
            String contextNumber = name.replaceAll("[^0-9]", "");
            ArchEdContext context = new ArchEdContext(contextNumber);
            listOfContexts.add(context);
        }
        return listOfContexts;
    }


    public List<ArchEdContext> parseContent(){
        List<ArchEdContext> listOfContexts = new ArrayList<>();
        try {
            String content = getAllContent();
            listOfContexts = getListOfContexts(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfContexts;
    }
}
