package Lab3Views;

import Lab3Controllers.DepartmentController;
import Lab3Models.Department;

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

class DepartmentTableModel extends DefaultTableModel {
    public DepartmentTableModel() {
    }

    public DepartmentTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public DepartmentTableModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DepartmentTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DepartmentTableModel(Vector<? extends Vector> data, Vector<?> columnNames) {
        super(data, columnNames);
    }

    public DepartmentTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class DepartmentViewForm{
    private JPanel departmentMainPanel;
    private JTextField idTextField;
    private JPanel addDepartmentForm;
    private JPanel idFormInput;
    private JLabel idLabel;
    private JTextField nameTextField;
    private JTextField managerTextField;
    private JButton addDepartmentButton;
    private JLabel nameLabel;
    private JLabel managerLabel;
    private JPanel nameFormInput;
    private JPanel managerFormInput;
    private JTable departmentTable;
    private JPanel modifyDepartmentForm;
    private JPanel idFormInputModify;
    private JPanel nameFormInputModify;
    private JPanel managerFormInputModify;
    private JButton modifyDepartmentButton;
    private JTextField managerTextFieldModify;
    private JTextField nameTextFieldModify;
    private JTextField idTextFieldModify;
    private JLabel modifyDepartmentLabel;
    private JLabel addDepartmentLabel;
    private JLabel departmentListLabel;
    private JLabel nameLabelModify;
    private JLabel idLabelModify;
    private JLabel managerLabelModify;
    private JTextField searchDepartmentTextField;
    private JButton searchDepartmentButton;
    private JPanel searchDepartmentPanel;
    private JLabel searchDepartmentLabel;
    private DepartmentController departmentController;
    private DepartmentTableModel departmentTableModel;

    public DepartmentViewForm() {
        idTextFieldModify.setEditable(false);
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("ID: %s \n", idTextField.getText()));
                System.out.print(String.format("Name: %s \n", nameTextField.getText()));
                System.out.print(String.format("Manager: %s \n", managerTextField.getText()));
                departmentController.addDepartment(
                        idTextField.getText(),
                        nameTextField.getText(),
                        managerTextField.getText()
                );
            }
        });

        modifyDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("ID: %s \n", idTextFieldModify.getText()));
                System.out.print(String.format("Name: %s \n", nameTextFieldModify.getText()));
                System.out.print(String.format("Manager: %s \n", managerTextFieldModify.getText()));
                departmentController.modifyDepartment(
                        idTextField.getText(),
                        nameTextField.getText(),
                        managerTextField.getText()
                );
            }
        });

        searchDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.print(String.format("Clicked Button: %s \n", event.getActionCommand()));
                System.out.print(String.format("Search Text: %s \n", searchDepartmentTextField.getText()));
                departmentController.showDepartmentFilteredData(searchDepartmentTextField.getText());
            }
        });

        departmentTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.print(String.format("Double clicked row: %s \n", row));
                    String oldId = (String) table.getValueAt(row, 0);
                    String oldName = (String) table.getValueAt(row, 1);
                    String oldManager = (String) table.getValueAt(row, 2);

                    idTextFieldModify.setText(oldId);
                    nameTextFieldModify.setText(oldName);
                    managerTextFieldModify.setText(oldManager);
                }
            }
        });
    }

    public JPanel getDepartmentMainPanel() {
        return departmentMainPanel;
    }

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    public void setDepartmentController(DepartmentController departmentController) {
        this.departmentController = departmentController;
    }

    public boolean fillDepartmentList(List<String> headers, List<Department> departmentList){
        List<Object[]> arrayDepartmentList = new ArrayList<>();
        for( Department department: departmentList){
            arrayDepartmentList.add(department.getValues());
        }
        departmentTableModel = new DepartmentTableModel(arrayDepartmentList.toArray(new Object[][] {}), headers.toArray());
        departmentTable.setModel(departmentTableModel);
        return true;
    }

}
