package Lab3Views;

import javax.swing.*;
import java.awt.*;

public class MainView extends javax.swing.JFrame {
    private JTabbedPane tabPane;
    private EmployeeViewForm employeeViewForm;
    private JPanel employeeTab;
    private JPanel departmentTab;
    private DepartmentViewForm departmentViewForm;
    private JPanel aboutTab;

    public MainView() throws HeadlessException {
        super();
        this.setSize(1200, 800);

        this.tabPane = new JTabbedPane();

        this.employeeViewForm = new EmployeeViewForm();
        this.employeeTab = employeeViewForm.getEmployeeMainPanel();
        this.tabPane.add("Employees", employeeTab);

        this.departmentViewForm = new DepartmentViewForm();
        this.departmentTab = departmentViewForm.getDepartmentMainPanel();
        this.tabPane.add("Departments", departmentTab);

        this.aboutTab = new AboutView();
        this.tabPane.add("About", aboutTab);

        this.add(tabPane, BorderLayout.NORTH);
    }

    public MainView(String title) throws HeadlessException {
        this();
        this.setTitle(title);
    }

    public MainView(String title, LayoutManager layout) throws HeadlessException {
        this();
        this.setTitle(title);
        this.setLayout(layout);
    }

    public EmployeeViewForm getEmployeeViewForm() {
        return employeeViewForm;
    }

    public JPanel getEmployeeTab() {
        return employeeTab;
    }

    public void setEmployeeTab(JPanel employeeTab) {
        this.employeeTab = employeeTab;
    }

    public DepartmentViewForm getDepartmentViewForm() {
        return departmentViewForm;
    }

    public JPanel getDepartmentTab() {
        return departmentTab;
    }

    public void setDepartmentTab(JPanel departmentTab) {
        this.departmentTab = departmentTab;
    }

    public JPanel getAboutTab() {
        return aboutTab;
    }

    public void setAboutTab(JPanel aboutTab) {
        this.aboutTab = aboutTab;
    }

    public static void main(String[] args) {
        MainView mainView = new MainView("Lab 3 MVC");
        mainView.setVisible(true);
    }
}
