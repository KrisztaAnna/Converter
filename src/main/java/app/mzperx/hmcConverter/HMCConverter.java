package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;


public class HMCConverter implements Converter{

    private TextFileLoader textLoader;
    private TextFileWriter textWriter;
    private TextFileParser textParser;

    public HMCConverter(String inputLocation, String outputLocation){
        this.textLoader = new TextFileLoader(inputLocation);
        this.textWriter = new TextFileWriter(outputLocation);
        this.textParser = new TextFileParser();
    }

    private String getContent(){
        return textLoader.getContent();

    }

    public void parseContent(){
        String content = getContent();
        textParser.parseContent(content);
    }



}
