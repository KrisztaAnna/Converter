package app.mzperx.hmcConverter;

import app.mzperx.matrices.ArchEdContext;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileParser {


    public void parseContent(String content){
        try {
            getContextNumber(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getContextNumber(String content) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("\\n.\\d{5}.");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String contextNumber = matcher.group(0);
            String name = contextNumber.replaceAll("[^0-9]","");
            ArchEdContext context = new ArchEdContext(contextNumber);
            System.out.println(name);
        }

    }
}
