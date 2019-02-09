package app.mzperx.hmcConverter;

import app.mzperx.converter.Converter;
import app.mzperx.matrices.ArchEdContext;

import java.io.File;
import java.util.List;

public class HMCConverter implements Converter{

    private ContextExtractor contextExtractor;
    private TextFileWriter textFileWriter;

    public HMCConverter(String inputLocation, String outputLocation){
        this.contextExtractor = new ContextExtractor(new File(inputLocation));
        this.textFileWriter = new TextFileWriter(outputLocation);
    }


    public void convert(){
        List<ArchEdContext> contexts = contextExtractor.parseContent();
        textFileWriter.setContexts(contexts);
        textFileWriter.contextListToFile(contexts);
    }


}
