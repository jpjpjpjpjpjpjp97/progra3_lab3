package Lab3Views;

import Lab3Controllers.EmployeeController;
import Lab3Models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class EmployeeTableModel extends DefaultTableModel {
    public EmployeeTableModel() {
    }

    public EmployeeTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public EmployeeTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public EmployeeTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public EmployeeTableModel(Vector<? extends Vector> data, Vector<?> columnNames) {
        super(data, columnNames);
    }

    public EmployeeTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class EmployeeViewForm {
    private JPanel addEmployeeForm;
    private JPanel idFormInput;
    private JLabel idLabel;
    private JTextField idTextField;
    private JPanel nameFormInput;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JPanel departmentFormInput;
    private JLabel departmentLabel;
    private JTextField departmentTextField;
    private JButton addEmployeeButton;
    private JLabel addEmployeeLabel;
    private JPanel modifyEmployeeForm;
    private JPanel idFormInputModify;
    private JLabel idLabelModify;
    private JTextField idTextFieldModify;
    private JPanel nameFormInputModify;
    private JLabel nameLabelModify;
    private JTextField nameTextFieldModify;
    private JPanel departmentFormInputModify;
    private JLabel departmentLabelModify;
    private JTextField departmentTextFieldModify;
    private JButton modifyEmployeeButton;
    private JLabel modifyEmployeeLabel;
    private JTable employeeTable;
    private JLabel EmployeeListLabel;
    private JPanel searchEmployeePanel;
    private JLabel searchEmployeeLabel;
    private JTextField searchEmployeeTextField;
    private JButton searchEmployeeButton;
    private JPanel employeeMainPanel;
    private JScrollPane employeeTablePane;
    private JPanel employeeAlertPanel;
    private JLabel employeeAlertLabel;
    private JButton employeDismissAlertButton;
    private JPanel mainListPanel;
    private EmployeeController employeeController;
    private EmployeeTableModel employeeTableModel;

    public EmployeeViewForm() {
        idTextFieldModify.setEditable(false);
        employeeAlertPanel.setVisible(false);
        employeDismissAlertButton.setSize(40, 40);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("ID: %s \n", idTextField.getText()));
                System.out.print(String.format("Name: %s \n", nameTextField.getText()));
                System.out.print(String.format("Department: %s \n", departmentTextField.getText()));
                if(employeeController.addEmployee(
                        idTextField.getText(),
                        nameTextField.getText(),
                        departmentTextField.getText()
                )){
                    idTextField.setText("");
                    nameTextField.setText("");
                    departmentTextField.setText("");
                }
            }
        });

        modifyEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("ID: %s \n", idTextFieldModify.getText()));
                System.out.print(String.format("Name: %s \n", nameTextFieldModify.getText()));
                System.out.print(String.format("Department: %s \n", departmentTextFieldModify.getText()));
                if (employeeController.modifyEmployee(
                    idTextFieldModify.getText(),
                    nameTextFieldModify.getText(),
                    departmentTextFieldModify.getText()
                )){
                    idTextFieldModify.setText("");
                    nameTextFieldModify.setText("");
                    departmentTextFieldModify.setText("");
                }
            }
        });

        searchEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("Search Text: %s \n", searchEmployeeTextField.getText()));
                employeeController.showEmployeeFilteredData(searchEmployeeTextField.getText());
            }
        });

        employeeTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.print(String.format("Double clicked row: %s \n", row));
                    String oldId = (String) table.getValueAt(row, 0);
                    String oldName = (String) table.getValueAt(row, 1);
                    String oldDepartment = (String) table.getValueAt(row, 2);

                    idTextFieldModify.setText(oldId);
                    nameTextFieldModify.setText(oldName);
                    departmentTextFieldModify.setText(oldDepartment);
                }
            }
        });

        employeDismissAlertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dismissAlert();
            }
        });
    }

    public JPanel getEmployeeMainPanel() {
        return employeeMainPanel;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public boolean fillEmployeeList(List<String> headers, List<Employee> employeeList){
        List<Object[]> arrayEmployeeList = new ArrayList<>();
        for( Employee employee: employeeList){
            arrayEmployeeList.add(employee.getValues());
        }
        employeeTableModel = new EmployeeTableModel(arrayEmployeeList.toArray(new Object[][] {}), headers.toArray());
        employeeTable.setModel(employeeTableModel);
        return true;
    }

    public void throwAlert(String message){
        employeeAlertLabel.setText(message);
        employeeAlertPanel.setVisible(true);
    }
    public boolean dismissAlert(){
        try{
            employeeAlertPanel.setVisible(false);
            return true;
        }
        catch (Exception error){
            System.out.print(String.format("Error: %s \n", error));
            return false;
        }
    }
}
