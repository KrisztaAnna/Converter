package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;
import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.util.List;

public class HMCConverter implements Converter{

    private ContextCreator contextCreator;
    private TextFileWriter textFileWriter;

    public HMCConverter(String inputLocation, String outputLocation){
        this.contextCreator = new ContextCreator(new File(inputLocation));
        this.textFileWriter = new TextFileWriter(outputLocation);
    }


    public void convert(){
        List<ArchEdContext> contexts = contextCreator.parseContent();
        textFileWriter.setContexts(contexts);
//        for (ArchEdContext context : contexts){
//            System.out.println(context.toString());
//        }
//        textFileWriter.contextListToFile(contexts);
    }



}
