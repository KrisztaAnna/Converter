package app.mzperx.hmcConverter;

import app.mzperx.exception.ListsAreNotTheSameSizeException;
import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextExtractor {

    private File inputFile;

    ContextExtractor(File inputFile){
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

    private  List<String> getListOfContextData(String content){
        List<String> records = new ArrayList<>();
        String[] recordsToAdd = content.split("\\n.\\d{5}.");
        for (String record : recordsToAdd){
            records.add(record);
        }
        records.remove(0);
        return records;
    }

    private List<ArchEdContext> addContextDataToInformationToSortField(List<ArchEdContext> listOfContexts, List<String> listOfContextData) throws ListsAreNotTheSameSizeException {
        int a = listOfContexts.size();
        int b = listOfContextData.size();

        if (a == b){
            List<ArchEdContext> contextsWithData = new ArrayList<>();
            for (int i = 0; i < listOfContexts.size(); i++) {
                String informationToSort = listOfContextData.get(i);
                listOfContexts.get(i).setInformationToSort(informationToSort);
                contextsWithData.add(listOfContexts.get(i));
            }
            return contextsWithData;
        }
        throw new ListsAreNotTheSameSizeException();
    }


    public List<ArchEdContext> parseContent(){
        List<ArchEdContext> contexts = new ArrayList<>();
        String content = getAllContent();
        try {
            List<ArchEdContext> listOfContexts = getListOfContexts(content);
            List<String> listOfContextData = getListOfContextData(content);
            contexts = addContextDataToInformationToSortField(listOfContexts, listOfContextData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ListsAreNotTheSameSizeException e) {
            System.out.println(e.getMessage());
        }
        return contexts;
    }
}

