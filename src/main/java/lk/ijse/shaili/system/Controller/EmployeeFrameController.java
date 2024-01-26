package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

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
        this.employeeService = ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
        this.attendanceService = ServiceFactory.getInstance().getService(ServiceTypes.ATTEND);
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
            System.out.println(search);

            selectedUser = employeeService.searchEmployee(search,selectedtype);
            System.out.println(selectedUser);
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
        employeeDTO dto = makeObject();
        System.out.println(dto);
        this.employeeService = ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
        employeeDTO employee = employeeService.saveEmployee(dto);
        if(dto.equals(employee)){
            System.out.println(employee);
            new Alert(Alert.AlertType.CONFIRMATION,"Sucessfully added.").show();
        }

    }

    private employeeDTO makeObject() {
        employeeDTO eDTO = new employeeDTO(txtid.getText(),txtname.getText(),dop.getValue().toString(),txtaddress.getText(),
                cmbtype.getValue().toString(),txtcontact.getText(), Double.parseDouble(txtsalary.getText()),txtnic.getText());
        return eDTO;
    }

    public void UpdateEmployeeOnAction(ActionEvent actionEvent) {
        employeeDTO dto = makeObject();
        this.employeeService = ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
        employeeDTO employee = employeeService.updateEmployee(dto);
        if(dto.equals(employee)){
            new Alert(Alert.AlertType.CONFIRMATION,"Sucessfully Updated.").show();
        }

    }

    public void DeleteEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the Employee?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            this.employeeService = ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
            boolean b = employeeService.deleteEmployee(txtid.getText());
            if (b){
                ClearEmployeeOnAction(actionEvent);
                new Alert(Alert.AlertType.INFORMATION,"MemberDTO delete successful").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"MemberDTO cannot deleted.").show();
            }
        }
    }

    public void ClearEmployeeOnAction(ActionEvent actionEvent) {
        txtid.clear();
        txtname1.clear();
        txtname.clear();
        txtnic.clear();
        txtsalary.clear();
        txtaddress.clear();
        txtattend.clear();
        txtbra.clear();
        dop.setValue(null);
        cmbtype.setValue(null);
        txtcontact.clear();
        txtadvance.clear();
        txtworkfee.clear();
        txttotal.clear();
        txtotfee.clear();
        txtbonus.clear();
        txtbasic.clear();
        cmbtype.setValue(null);
    }

    public void newEmployeeIdOnAction(ActionEvent actionEvent) throws SQLException {

        try {
            String empid = employeeService.generateNewEmployeeId();
            txtid.setText(empid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void HomepageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.AHOME,frame);
    }

    public void calculatetotalOnAction(ActionEvent actionEvent) {
        double basic = Double.parseDouble(txtbasic.getText());
        double bonus = Double.parseDouble(txtbonus.getText());
        double advance = Double.parseDouble(txtadvance.getText());
        double bra = Double.parseDouble(txtbra.getText());
        double workfee = Double.parseDouble(txtworkfee.getText());
        double otfees = Double.parseDouble(txtotfee.getText());
        double total = (basic+bonus+bra+workfee+otfees)-advance;
        txttotal.setText(String.valueOf(total));
    }

    public void displayPaysheetonAcction(ActionEvent actionEvent) {
        String workfee = txtworkfee.getText();
        String otfee = txtotfee.getText();
        String bonus = txtbonus.getText();
        String bra = txtbra.getText();
        String attend = txtattend.getText();
        String basic = txtbasic.getText();
        String advance = txtadvance.getText();
        double[] totalarry =calculate();
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("employeename",selectedUser.getName());
        hm.put("Attend",attend);
        hm.put("Basic",basic);
        hm.put("workfees",workfee);
        hm.put("otfees",otfee);
        hm.put("br",bra);
        hm.put("Bonus",bonus);
        hm.put("advance",advance);
        String tot1 = String.valueOf(totalarry[0]);
        String tot2 =String.valueOf(totalarry[1]);
        String total = String.valueOf(totalarry[2]);
        System.out.println(total);
        hm.put("tot1",tot1);
        hm.put("tot2",tot2);
        hm.put("total",total);

        try {
            JasperReport compileReport = JasperCompileManager.compileReport("src\\main\\resources\\Report\\employeepaysheet.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hm, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private double[] calculate() {
        double fees = Double.parseDouble(txtbasic.getText());
        double tot1 = fees+totfee+otfee;
        double bra = Double.parseDouble(txtbra.getText());
        double bonus = Double.parseDouble(txtbonus.getText());
        double advance = Double.parseDouble(txtadvance.getText());
        double tot2 = (bra+bonus)-advance;
        double tot3 = tot1+tot2;
        double[] total ={tot1,tot2,tot3};
        return total;
    }

    public void getmonthOnAction(ActionEvent actionEvent) {
        month = cmbmonth.getSelectionModel().getSelectedItem().toString();
        attendanceDTO = attendanceService.searchAttendance(selectedUser.getEid(),month);
        if (month.equals("December")){
            txtbonus.setText(String.valueOf(salaryDTO.getDcbonus()));
        }else if (month.equals("April")){
            txtbonus.setText(String.valueOf(salaryDTO.getApbonus()));
        } else{
            txtbonus.setText("0.0");
        }
        txtbra.setText(String.valueOf(salaryDTO.getBra()));
        txtattend.setText(String.valueOf(attendanceDTO.getDaycount()));
        totfee = attendanceDTO.getNdays() * salaryDTO.getNdot();
        otfee = attendanceDTO.getOdays() * salaryDTO.getSdot();
        txtworkfee.setText(String.valueOf(totfee));
        txtotfee.setText(String.valueOf(otfee));
    }
}
