package Lab3Models;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeParser {
    final String filePath = "employeeList.xml";
    List<String> employeeHeaders;
    List<Employee> employeeList;

    // Begin::Test Data
    final List<String> headers = new ArrayList<>(List.of(new String[]{"ID", "Name", "Department"}));
    // End::Test Data

    public String getFilePath() {
        return filePath;
    }

    public List<String> getEmployeeHeaders() {
        return employeeHeaders;
    }

    public void setEmployeeHeaders(List<String> employeeHeaders) {
        this.employeeHeaders = employeeHeaders;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<String> getHeaders() throws FileNotFoundException, IOException {
        try{
            employeeHeaders = headers;
            return employeeHeaders;
        }catch (Exception error){
            System.out.print("Error: " + error);
            return null;
        }
    }

    public List<Employee> getData(String employeeId) throws FileNotFoundException, IOException{
        try{
            NodeList employeeNodeList = new EmployeeParser().getEmployeeListFromFile();
            employeeList = new ArrayList<Employee>();
            if (employeeId == "-1" || employeeId.isBlank()){
                for (int employeeIndex = 0; employeeIndex < employeeNodeList.getLength(); employeeIndex++) {
                    Node employee = employeeNodeList.item(employeeIndex);
                    Element employeeElement = (Element) employee;
                    employeeList.add(new Employee(
                            employeeElement.getAttribute("ID"),
                            employeeElement.getElementsByTagName("Name").item(0).getTextContent(),
                            employeeElement.getElementsByTagName("Department").item(0).getTextContent()
                        )
                    );
                }
            }
            else {
                for (int employeeIndex = 0; employeeIndex < employeeNodeList.getLength(); employeeIndex++) {
                    Element employeeElement = (Element) employeeNodeList.item(employeeIndex);
                    if (employeeElement.getAttribute("ID").equals(employeeId)){
                        employeeList.add(new Employee(
                                employeeElement.getAttribute("ID"),
                                employeeElement.getElementsByTagName("Name").item(0).getTextContent(),
                                employeeElement.getElementsByTagName("Department").item(0).getTextContent()
                            )
                        );
                    }
                }
            }
            return employeeList;
        }catch (Exception error){
            System.out.print("Error: " + error);
            return null;
        }
    }

    public boolean setData() throws FileNotFoundException, IOException{
        try{
            return true;
        }catch (Exception error){
            System.out.print("Error: " + error);
            return false;
        }
    }
    public boolean addEmployee(Employee newEmployee){
        try{
            MainParser mainParser = new MainParser();
            Document document = mainParser.readDocument(filePath);
            Element employeesElement = document.getDocumentElement();
            Element employeeElement = document.createElement("Employee");
            employeeElement.setAttribute("ID", newEmployee.getId());
            Element nameElement = document.createElement("Name");
            nameElement.appendChild(document.createTextNode(newEmployee.getName()));
            Element departmentElement = document.createElement("Department");
            departmentElement.appendChild(document.createTextNode(newEmployee.getDepartment()));

            employeeElement.appendChild(nameElement);
            employeeElement.appendChild(departmentElement);
            employeesElement.appendChild(employeeElement);

            mainParser.writeToDocument(document, filePath);
            return true;

        }catch (Exception error){
            System.out.print("Error: " + error);
            return false;
        }

    }

    public boolean modifyEmployee(String employeeId, Employee employee) throws FileNotFoundException {
        try {
            MainParser mainParser = new MainParser();
            Document document = mainParser.readDocument(filePath);
            NodeList employeeList = document.getElementsByTagName("Employee");
            for (int employeeIndex = 0; employeeIndex < employeeList.getLength(); employeeIndex++) {
                if (employeeId.equals(employeeList.item(employeeIndex).getAttributes().getNamedItem("ID").getTextContent())){
                    employeeList.item(employeeIndex).getChildNodes().item(0).setTextContent(employee.getName());
                    employeeList.item(employeeIndex).getChildNodes().item(1).setTextContent(employee.getDepartment());
                }
            }
            mainParser.writeToDocument(document, filePath);
            return true;
        }
        catch (Exception error){
            System.out.print("Error: " + error);
            return false;
        }
    }

    public NodeList getEmployeeListFromFile() throws FileNotFoundException {
        try {
            NodeList employeeList = new MainParser().readDocument(filePath).getElementsByTagName("Employee");
            return employeeList;
        } catch (FileNotFoundException error){
            System.out.print("Error (getEmployeeListFromFile): " + error);
            throw error;
        }
    }

    public Node getEmployeeFromFile(int index){
        try {
            NodeList employeeList = getEmployeeListFromFile();
            return employeeList.item(index);
        }catch (Exception error){
            System.out.print("Error: " + error);
            return null;
        }
    }
}
