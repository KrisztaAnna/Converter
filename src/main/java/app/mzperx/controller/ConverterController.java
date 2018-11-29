package app.mzperx.controller;

import app.mzperx.converter.Converter;
import app.mzperx.jxltomif.JxlToMifConverter;

import java.io.File;
import java.util.Scanner;

public class ConverterController {

    private String getPath(String direction){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Path to the " + direction + " file: ");
        String path = scanner.nextLine();
        return path;
    }

    private String getOutputLocation(){
        return getPath("output");
    }

    private File getInputFile(){
        return new File(getPath("input"));
    }

    public Converter chooseConverter(){
        File input = getInputFile();
        String outputLocation = getOutputLocation();

        return new JxlToMifConverter(input, outputLocation);
    }
}

