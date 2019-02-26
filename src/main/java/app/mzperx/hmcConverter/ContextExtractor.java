package app.mzperx.hmcConverter;

import app.mzperx.exception.ListsAreNotTheSameSizeException;
import app.mzperx.hmcConverter.dao.implementation.memory.ArchedContextDaoMem;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextExtractor {

    private static final Logger logger = LoggerFactory.getLogger(ArchEdContext.class);
    private ArchedContextDaoMem archedContextDaoMem = ArchedContextDaoMem.getInstance();
    private File inputFile;

    ContextExtractor(File inputFile){
        this.inputFile = inputFile;
    }

    private String getFileContent() {
        logger.info("Reading input file...");
        try {
            Scanner scanner = new Scanner(inputFile);
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            return content;
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage());
        }
        return null;
    }

    private List<ArchEdContext> createArchEdContextsWithName(String fileContent){
        logger.info("Extracting contexts from input file...");
        List<ArchEdContext> namedContexts = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\n.?\\d{5}.?");
        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            String name = matcher.group(0);
            String contextNumber = name.replaceAll("[^0-9]", "");
            ArchEdContext context = new ArchEdContext(contextNumber);
            namedContexts.add(context);
        }
        logger.info("Number of named contexts extracted: " + namedContexts.size() + ".");
        return namedContexts;
    }

    private  List<String> getListOfContextData(String fileContent){
        logger.info("Extracting context data...");
        List<String> records = new ArrayList<>();
        String[] recordsToAdd = fileContent.split("\\n.?\\d{5}.?");
        records.addAll(Arrays.asList(recordsToAdd));
        records.remove(0);
        logger.info("Data have been extracted for " + records.size() + " contexts.");
        return records;
    }

    private List<ArchEdContext> addContextDataToInformationToSortField(List<ArchEdContext> listOfNamedContexts, List<String> listOfContextData) throws ListsAreNotTheSameSizeException {
        int a = listOfNamedContexts.size();
        int b = listOfContextData.size();

        if (a == b){
            logger.info("Merging context data with contexts...");
            List<ArchEdContext> namedContextsWithData = new ArrayList<>();
            for (int i = 0; i < listOfNamedContexts.size(); i++) {
                String contextData = listOfContextData.get(i);
                listOfNamedContexts.get(i).setInformationToSort(contextData);
                namedContextsWithData.add(listOfNamedContexts.get(i));
            }
            return namedContextsWithData;
        }
        ListsAreNotTheSameSizeException e = new ListsAreNotTheSameSizeException();
        logger.error(e.getMessage(),e);
        throw e;
    }

    private void setCONTEXTS(String fileContent) throws FileNotFoundException, ListsAreNotTheSameSizeException {
        logger.info("Setting up list of context objects...");
        List<ArchEdContext> listOfNamedContexts = createArchEdContextsWithName(fileContent);
        List<String> listOfContextData = getListOfContextData(fileContent);
        List<ArchEdContext> namedContextsWithData = addContextDataToInformationToSortField(listOfNamedContexts, listOfContextData);
        for (ArchEdContext context : namedContextsWithData){

            archedContextDaoMem.addContext(context);

        }
        logger.info(this.archedContextDaoMem.getAllContexts().size() + " context objects have been created and added data to.");
    }

    private void finalizeContexts(){
        logger.info("Sorting context data into fields...");
        for (ArchEdContext context : archedContextDaoMem.getAllContexts()){
            context.sortInformation();
        }
    }

    public List<ArchEdContext> txtToArchEdContextList(){
        logger.info("Parsing txt file...");
        String fileContent = getFileContent();
        try {
            setCONTEXTS(fileContent);
            finalizeContexts();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ListsAreNotTheSameSizeException e) {
            logger.error(e.getMessage());
        }
        logger.info("Parsing finished. List of ArchEd contexts have been created.");
        return archedContextDaoMem.getAllContexts();
    }
}

