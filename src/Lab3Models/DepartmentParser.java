package Lab3Models;

import org.w3c.dom.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentParser {

    final String filePath = "";
    List<String> departmentHeaders;
    List<Department> departmentList;

    // Begin::Test Data
    final List<String> headers = new ArrayList<>(List.of(new String[]{"ID", "Name", "Manager"}));
    // End::Test Data

    public List<String> getHeaders() throws FileNotFoundException, IOException {
        try{
            departmentHeaders = headers;
            return departmentHeaders;
        }catch (Exception error){
            System.out.print("Error: " + error);
            return null;
        }
    }

    public List<Department> getData(String departmentId) throws FileNotFoundException, IOException{
        try{
            NodeList departmentNodeList = new DepartmentParser().getDepartmentList("departmentList.xml");
            departmentList = new ArrayList<Department>();
            if (departmentId == "-1" || departmentId.isBlank()){
                for (int departmentIndex = 0; departmentIndex < departmentNodeList.getLength(); departmentIndex++) {
                    Node department = departmentNodeList.item(departmentIndex);
                    Element departmentElement = (Element) department;
                    departmentList.add(new Department(
                            departmentElement.getAttribute("ID"),
                            departmentElement.getElementsByTagName("Name").item(0).getTextContent(),
                            departmentElement.getElementsByTagName("Manager").item(0).getTextContent()
                        )
                    );
                }
            }
            else {
                for (int departmentIndex = 0; departmentIndex < departmentNodeList.getLength(); departmentIndex++) {
                    Node department = departmentNodeList.item(departmentIndex);
                    Element departmentElement = (Element) department;
                    if (departmentElement.getAttribute("ID").equals(departmentId)){
                        departmentList.add(new Department(
                                departmentElement.getAttribute("ID"),
                                departmentElement.getElementsByTagName("Name").item(0).getTextContent(),
                                departmentElement.getElementsByTagName("Manager").item(0).getTextContent()
                            )
                        );
                    }
                }
            }
            return departmentList;
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

    public NodeList getDepartmentList (String file) throws FileNotFoundException {
        NodeList departmentList = new MainParser().readDocument(file).getElementsByTagName("Department");
        return departmentList;
    }

    public Node getDepartment(int index, String file) throws FileNotFoundException {
        NodeList departmentList = getDepartmentList(file);
        return departmentList.item(index);
    }
}
