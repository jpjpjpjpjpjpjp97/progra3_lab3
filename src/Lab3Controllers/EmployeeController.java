package Lab3Controllers;

import Lab3Models.Employee;
import Lab3Models.EmployeeParser;
import Lab3Models.MainParser;
import Lab3Views.EmployeeViewForm;
import Lab3Views.MainView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EmployeeController {
    private EmployeeViewForm employeeViewForm;
    public boolean showEmployeeData() throws FileNotFoundException, ParserConfigurationException {
        try {
            EmployeeParser employeeParser = new EmployeeParser();
            List<String> employeeHeaders = employeeParser.getHeaders();
            List<Employee> employeeData = employeeParser.getData("-1");
            employeeViewForm.fillEmployeeList(employeeHeaders, employeeData);
            return true;
        } catch (Exception error) {
            System.out.print("Error (showEmployeeData): " + error);
            try {
                employeeViewForm.throwAlert("Error while opening XML file. New file was created.");
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                Element employees = document.createElement("Employees");
                document.appendChild(employees);
                EmployeeParser employeeParser = new EmployeeParser();
                new MainParser().createDocument(document, employeeParser.getFilePath());
                NodeList employeeList = new MainParser().readDocument(employeeParser.getFilePath()).getElementsByTagName("Employee");
                List <String> employeeHeaders = employeeParser.getHeaders();
                List <Employee> employeeData = employeeParser.getData("-1");
                employeeViewForm.fillEmployeeList(employeeHeaders, employeeData);
                return true;
            } catch (ParserConfigurationException | FileNotFoundException innerError) {
                System.out.print("Inner Error (showEmployeeData): " + innerError);
                throw innerError;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean addEmployee(String id, String name, String department) {
        try {
            if (id.isBlank() || name.isBlank() || department.isBlank()){
                employeeViewForm.throwAlert("Error on adding employee: All employees must have an id, name and department.");
                return false;
            }
            new EmployeeParser().addEmployee(new Employee(id, name, department));
            this.showEmployeeData();
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public boolean modifyEmployee(String id, String name, String department) {
        try {
            if (id.isBlank() || name.isBlank() || department.isBlank()){
                employeeViewForm.throwAlert("Error on updating employee: All employees must have an id, name and department.");
                return false;
            }
            new EmployeeParser().modifyEmployee(id, new Employee(id, name, department));
            this.showEmployeeData();
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public boolean showEmployeeFilteredData(String employeeID) {
        try {
            EmployeeParser employeeParser = new EmployeeParser();
            List<String> employeeHeaders = employeeParser.getHeaders();
            List<Employee> employeeData = employeeParser.getData(employeeID);
            employeeViewForm.fillEmployeeList(employeeHeaders, employeeData);
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public EmployeeViewForm getEmployeeViewForm() {
        return employeeViewForm;
    }

    public void setEmployeeViewForm(EmployeeViewForm employeeViewForm) {
        this.employeeViewForm = employeeViewForm;
    }
}
