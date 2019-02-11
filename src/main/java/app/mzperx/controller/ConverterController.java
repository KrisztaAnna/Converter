package app.mzperx.controller;

import app.mzperx.converter.Converter;
import app.mzperx.hmcConverter.HMCConverter;
import app.mzperx.hmcConverter.model.ArchEdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConverterController {
    private static final Logger logger = LoggerFactory.getLogger(ArchEdContext.class);

    public Converter chooseConverter(){
        String inputLocation = getInputLocation();
        String outputLocation = getOutputLocation();

        return new HMCConverter(inputLocation, outputLocation);
    }

    private String getOutputLocation(){
        String path = getPath("output");
        logger.info("Output path and file name: " + path);
        return path;
    }

    private String getInputLocation(){
        String path = getPath("input");
        logger.info("Input path and file name: " + path);
        return path;
    }

    //Gets path to either input or output file through user input
    private String getPath(String direction){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Path to the " + direction + " file: ");
        String path = scanner.nextLine();
        return path;
    }
}

