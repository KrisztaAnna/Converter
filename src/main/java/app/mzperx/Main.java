package app.mzperx;

import app.mzperx.controller.ConverterController;
import app.mzperx.converter.Converter;
import app.mzperx.hmcConverter.HMCConverter;

import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ConverterController controller = new ConverterController();

       Converter converter = controller.chooseConverter();
       converter.parseContent();

    }
}
