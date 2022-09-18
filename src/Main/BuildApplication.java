package Main;

import Lab3Controllers.MainController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class BuildApplication {
    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException {
        new MainController().startApplication();
    }
}
