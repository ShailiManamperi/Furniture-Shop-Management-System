package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.shaili.system.Dto.CustomerDTO;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.CustomerService;

import java.sql.SQLException;

public class CustomerdetailframeCOntroller {
    public Label lbltitle;
    public JFXTextField txtcustid;
    public JFXTextField txtcustname;
    public JFXTextField txtaddress;
    public JFXTextField txtnic;
    public JFXTextField txtcontact;
    public JFXButton btnAdd;

    public CustomerService customerService;
    public void initialize() throws SQLException, ClassNotFoundException {
        this.customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
        try {
            String customerId = customerService.generateNewCustomerId();
            txtcustid.setText(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addCustomerOnAction(ActionEvent actionEvent) {
        boolean c1 = customerService.searchDuplicate(txtcustid.getText());
        if (c1){
            CustomerDTO c2 = makeObject();

            CustomerDTO saveCustomer = customerService.saveCustomer(c2);
            if (saveCustomer != null){
                new Alert(Alert.AlertType.INFORMATION, "New Customer Added Succesful").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong !").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "This Customer id already added !").show();
        }

    }

    private CustomerDTO makeObject() {
        String id = txtcustid.getText();
        String name = txtcustname.getText();
        String  nic = txtnic.getText();
        String contact = txtcontact.getText();
        String address = txtaddress.getText();
        CustomerDTO c1 = new CustomerDTO(id,name,address,nic,contact);
        return c1;
    }
}
