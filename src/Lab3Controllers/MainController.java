package Lab3Controllers;

import Lab3Views.MainView;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class MainController {
    DepartmentController departmentController;
    EmployeeController employeeController;
    MainView mainView;
    public void startApplication() throws FileNotFoundException, ParserConfigurationException {
        mainView = new MainView("Lab 3 MVC");

        departmentController = new DepartmentController();
        departmentController.setDepartmentViewForm(mainView.getDepartmentViewForm());
        departmentController.showDepartmentData();
        employeeController = new EmployeeController();
        employeeController.setEmployeeViewForm(mainView.getEmployeeViewForm());
        employeeController.showEmployeeData();

        mainView.getDepartmentViewForm().setDepartmentController(departmentController);
        mainView.getEmployeeViewForm().setEmployeeController(employeeController);
        mainView.setVisible(true);
    }
}
