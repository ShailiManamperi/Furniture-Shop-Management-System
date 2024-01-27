package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.UserService;
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class CashierFrameController {
    public AnchorPane pane;
    public Rectangle rectangle;
    public AnchorPane frame;
    public JFXButton btnOrder;
    public JFXButton btnLogout;

    UserDTO u1 = LoginFrameController.u1;
    public UserService userService;

    public void initialize() throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/orderFrame.fxml"));
        frame.getChildren().clear();
        frame.getChildren().add(load);
    }
    public void orderFrameOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER,frame);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        this.userService = ServiceFactory.getInstance().getService(ServiceTypes.USER);
        UserDTO u2 = new UserDTO(u1.getUsername(),u1.getType(),u1.getPassword(),"No", u1.getHint());
        boolean updateuser = userService.update(u2);
        if (updateuser){
            Navigation.navigate(Routes.SIGNIN, pane);
        }
    }
}
