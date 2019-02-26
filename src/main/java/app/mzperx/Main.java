package app.mzperx;

import app.mzperx.controller.ConverterController;
import app.mzperx.converter.Converter;
import app.mzperx.hmcConverter.HMCConverter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, JAXBException {
//        ConverterController controller = new ConverterController();
//        Converter converter = controller.chooseConverter();

//        Converter converter = new HMCConverter("/Users/krisztinabaranyai/Desktop/Area_D.txt",
//                                               "/Users/krisztinabaranyai/Desktop/Area_D_output.txt");
//        converter.convert();


        Converter converter2 = new HMCConverter("/Users/krisztinabaranyai/Desktop/Area_D.txt",
                                               "/Users/krisztinabaranyai/Desktop/output.txt");
        converter2.convert();
//
//        Converter converter2 = new HMCConverter("/Users/krisztinabaranyai/Desktop/Area_R3.txt",
//                                               "/Users/krisztinabaranyai/Desktop/Area_R3_output.txt");
//        converter2.convert();

    }
}
