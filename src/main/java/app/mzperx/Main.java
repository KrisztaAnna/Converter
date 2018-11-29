package app.mzperx;

import app.mzperx.controller.ConverterController;
import app.mzperx.converter.Converter;

public class Main {

    public static void main(String[] args){
        ConverterController controller = new ConverterController();

        Converter converter = controller.chooseConverter();
        converter.writeContent();
    }
}
