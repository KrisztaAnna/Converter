package app.mzperx.controller;

import app.mzperx.converter.Converter;
import app.mzperx.hmcConverter.HMCConverter;
import java.util.Scanner;

public class ConverterController {

    public Converter chooseConverter(){
        String inputLocation = getInputLocation();
        String outputLocation = getOutputLocation();

        return new HMCConverter(inputLocation, outputLocation);
    }

    private String getOutputLocation(){
        return getPath("output");
    }

    private String getInputLocation(){
        return getPath("input");
    }

    //Gets path to either input or output file through user input
    private String getPath(String direction){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Path to the " + direction + " file: ");
        String path = scanner.nextLine();
        return path;
    }
}

