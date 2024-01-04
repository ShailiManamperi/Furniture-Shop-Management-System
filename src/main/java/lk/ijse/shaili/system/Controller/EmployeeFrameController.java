package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaili.system.Dto.AttendanceDTO;
import lk.ijse.shaili.system.Dto.SalaryDTO;
import lk.ijse.shaili.system.Dto.employeeDTO;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.AttendanceService;
import lk.ijse.shaili.system.Service.custom.EmployeeService;

import java.time.LocalDate;

public class EmployeeFrameController {

    public AnchorPane frame;
    public JFXTextField txtSearch;
    public ImageView imgsearch;
    public JFXTextField txtid;
    public JFXTextField txtname;
    public JFXDatePicker dop;
    public JFXTextField txtaddress;
    public JFXTextField txtcontact;
    public JFXTextField txtnic;
    public JFXTextField txtsalary;
    public JFXComboBox cmbtype;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXRadioButton rdbid;
    public JFXRadioButton rdbname;
    public JFXRadioButton rdbnic;
    public JFXButton btnNew;
    public Label lbltitle;
    public JFXButton btncalculate;
    public JFXTextField txttotal;
    public JFXButton btndiplay;
    public JFXTextField txtname1;
    public JFXComboBox cmbmonth;
    public JFXTextField txtbasic;
    public JFXTextField txtbra;
    public JFXTextField txtbonus;
    public JFXTextField txtadvance;
    public JFXTextField txtattend;
    public JFXTextField txtworkfee;
    public JFXTextField txtotfee;

    public static employeeDTO selectedUser  ;
    public static AttendanceDTO attendanceDTO;
    public static SalaryDTO salaryDTO;
    String month;
    double totfee;
    double otfee;

    public EmployeeService employeeService;
    public AttendanceService attendanceService;

    public void initialize()  {
        String[] type = {"Admin","Cashier","Shop Keeper"};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        cmbtype.setItems(list);
        cmbmonth.setPromptText("Select Month");
        String[] month = {"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
        ObservableList<String> list1 = FXCollections.observableArrayList(month);
        cmbmonth.setItems(list1);
        cmbmonth.setPromptText("Select Month");
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
    private void fillData() {
        txtname.setText(selectedUser.getName());
        txtaddress.setText(selectedUser.getAddress());
        txtcontact.setText(selectedUser.getContact());
        txtnic.setText(selectedUser.getNic());
        txtid.setText(selectedUser.getEid());
        txtsalary.setText(String.valueOf(selectedUser.getSalary()));
        String job = selectedUser.getJob();
        cmbtype.setValue(job);
        dop.setValue(LocalDate.parse(selectedUser.getDob()));
        txtname1.setText(selectedUser.getName());
        txtbasic.setText(String.valueOf(selectedUser.getSalary()));
    }

    private String selectedtype() {
        String type = null;
        if (rdbid.isSelected()){
            type ="E_id";
        }if (rdbname.isSelected()){
            type = "Name";
        }if (rdbnic.isSelected()){
            type = "Nic";
        }
        return type;
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String selectedtype = selectedtype();
        if (selectedtype == null){
            new Alert(Alert.AlertType.WARNING,"Please select the type frist").show();
        }else{
            String search = txtSearch.getText();
            this.employeeService = ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
            this.attendanceService = ServiceFactory.getInstance().getService(ServiceTypes.ATTEND);
            selectedUser = employeeService.searchEmployee(search,selectedtype);
            if (selectedUser == null){
                new Alert(Alert.AlertType.WARNING,"this type employee not founded!").show();
            }else {
                String type = selectedUser.getJob();
                salaryDTO = attendanceService.searchSalaryType(type);
                System.out.println(selectedUser.getEid());
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                fillData();
            }
        }
    }

    public void addEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void UpdateEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void DeleteEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void ClearEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void newEmployeeIdOnAction(ActionEvent actionEvent) {
    }

    public void HomepageOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void calculatetotalOnAction(ActionEvent actionEvent) {
    }

    public void displayPaysheetonAcction(ActionEvent actionEvent) {
    }

    public void getmonthOnAction(ActionEvent actionEvent) {
    }
}
