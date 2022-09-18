package Lab3Models;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainParser {
    public Document readDocument(String file) throws FileNotFoundException {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlBuilder = factory.newDocumentBuilder();
            Document xmlDocument = xmlBuilder.parse(file);
            xmlDocument.getDocumentElement().normalize();
            return xmlDocument;
        }catch (FileNotFoundException | ParserConfigurationException error){
            System.out.print(String.format("Error (readDocument): %s \n", error));
            throw new RuntimeException(error);
        } catch (IOException error) {
            System.out.print(String.format("Error (readDocument): %s \n", error));
            throw new RuntimeException(error);
        } catch (SAXException error) {
            System.out.print(String.format("Error (readDocument): %s \n", error));
            throw new RuntimeException(error);
        }
    }

    public boolean writeToDocument(Document document, String filePath){
        try{
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(filePath);
            transformer.transform(source, result);
            return true;
        }
        catch (Exception error){
            System.out.print(String.format("Error (writeToDocument): %s \n", error));
            return false;
        }
    }

    public boolean createDocument(Document document, String filePath){
        try{
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(filePath);
            transformer.transform(source, result);
            return true;
        }
        catch (Exception error){
            System.out.print(String.format("Error (createDocument): %s \n", error));
            return false;
        }
    }
}
