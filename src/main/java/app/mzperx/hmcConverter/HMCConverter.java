package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;
import java.io.File;

public class HMCConverter implements Converter{

    private ContextCreator contextCreator;
    private TextFileWriter textFileWriter;

    public HMCConverter(String inputLocation, String outputLocation){
        this.contextCreator = new ContextCreator(new File(inputLocation));
        this.textFileWriter = new TextFileWriter(outputLocation);
    }


    public void convert(){
        contextCreator.parseContent();
//        textFileWriter.writeParsedContent(content);
    }



}
