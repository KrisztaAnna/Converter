package app.mzperx.hmcConverter;

import app.mzperx.exception.ListsAreNotTheSameSizeException;
import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextExtractor {

    ArchedContextDaoMem archedContextDaoMem = ArchedContextDaoMem.getInstance();
    private File inputFile;

    ContextExtractor(File inputFile){
        this.inputFile = inputFile;
    }

    private String getFileContent() {
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

    private List<ArchEdContext> createArchEdContextsWithName(String fileContent) throws FileNotFoundException {
        List<ArchEdContext> namedContexts = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find()) {
            String name = matcher.group(0);
            String contextNumber = name.replaceAll("[^0-9]", "");
            ArchEdContext context = new ArchEdContext(contextNumber);

            namedContexts.add(context);
        }
        return namedContexts;
    }

    private  List<String> getListOfContextData(String fileContent){
        List<String> records = new ArrayList<>();
        String[] recordsToAdd = fileContent.split("\\n.\\d{5}.");
        records.addAll(Arrays.asList(recordsToAdd));
        records.remove(0);
        return records;
    }

    private List<ArchEdContext> addContextDataToInformationToSortField(List<ArchEdContext> listOfNamedContexts, List<String> listOfContextData) throws ListsAreNotTheSameSizeException {
        int a = listOfNamedContexts.size();
        int b = listOfContextData.size();

        if (a == b){
            List<ArchEdContext> namedContextsWithData = new ArrayList<>();
            for (int i = 0; i < listOfNamedContexts.size(); i++) {
                String contextData = listOfContextData.get(i);
                listOfNamedContexts.get(i).setInformationToSort(contextData);
                namedContextsWithData.add(listOfNamedContexts.get(i));
            }
            return namedContextsWithData;
        }
        throw new ListsAreNotTheSameSizeException();
    }

    private void setCONTEXTS(String fileContent) throws FileNotFoundException, ListsAreNotTheSameSizeException {
        List<ArchEdContext> listOfNamedContexts = createArchEdContextsWithName(fileContent);
        List<String> listOfContextData = getListOfContextData(fileContent);
        List<ArchEdContext> namedContextsWithData = addContextDataToInformationToSortField(listOfNamedContexts, listOfContextData);
        for (ArchEdContext context : namedContextsWithData){
            archedContextDaoMem.addContext(context);
        }
    }

    private void finalizeContexts(){
        for (ArchEdContext context : archedContextDaoMem.getAllContexts()){
            context.sortInformation();
        }
    }

    public List<ArchEdContext> parseContent(){
        String fileContent = getFileContent();
        try {
            setCONTEXTS(fileContent);
            finalizeContexts();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ListsAreNotTheSameSizeException e) {
            System.out.println(e.getMessage());
        }
        return archedContextDaoMem.getAllContexts();
    }
}

