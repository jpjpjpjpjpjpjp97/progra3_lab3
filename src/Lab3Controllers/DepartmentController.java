package Lab3Controllers;

import Lab3Models.Department;
import Lab3Models.DepartmentParser;
import Lab3Views.DepartmentViewForm;

import java.util.List;

public class DepartmentController {
    private DepartmentViewForm departmentViewForm;
    public boolean showDepartmentData() {
        try {
            DepartmentParser departmentParser = new DepartmentParser();
            List<String> departmentHeaders = departmentParser.getHeaders();
            List<Department> departmentData = departmentParser.getData("-1");
            departmentViewForm.fillDepartmentList(departmentHeaders, departmentData);
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public boolean addDepartment(String id, String name, String Manager) {
        try {
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public boolean modifyDepartment(String id, String name, String Manager) {
        try {
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public boolean showDepartmentFilteredData(String departmentID) {
        try {
            DepartmentParser departmentParser = new DepartmentParser();
            List<String> departmentHeaders = departmentParser.getHeaders();
            List<Department> departmentData = departmentParser.getData(departmentID);
            departmentViewForm.fillDepartmentList(departmentHeaders, departmentData);
            return true;
        } catch (Exception error) {
            System.out.print("Error: " + error);
            return false;
        }
    }

    public DepartmentViewForm getDepartmentViewForm() {
        return departmentViewForm;
    }

    public void setDepartmentViewForm(DepartmentViewForm departmentViewForm) {
        this.departmentViewForm = departmentViewForm;
    }
}
