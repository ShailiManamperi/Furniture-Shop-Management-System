package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.UserService;
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;
import lk.ijse.shaili.system.Util.Task.TimeTask;

import javax.naming.Name;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

public class AdminContentFrameController {
    public ImageView imgDashboard;
    public Label lblTime;
    public ImageView imgEmployee;
    public ImageView imgItem;
    public ImageView imgCustomer;
    public ImageView imgSupplier;
    public ImageView imgLogout;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnItem;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Label lblGreeting;

    @FXML
    private Label lblName;
    UserDTO u1 = LoginFrameController.u1;

    public UserService userService;


    public void initialize() throws IOException {
        setWelcome();
        setTime();
        Parent load = FXMLLoader.load(getClass().getResource("/View/main/AdminDashboardcontent.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
    public void setWelcome() {
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            if (currentTime.getHour() > 6 && currentTime.getHour() < 12) {
                lblGreeting.setText("Good Morning ");
            } else if (currentTime.getHour() >= 12 && currentTime.getHour() < 16) {
                lblGreeting.setText("Good AfterNoon");
            } else if (currentTime.getHour() >= 16 && currentTime.getHour() < 19) {
                lblGreeting.setText("Good Evening");
            } else {
                lblGreeting.setText("Good Night");
            }
        }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void setTime() {

        TimeTask a = new TimeTask();
        a.valueProperty().addListener((e,b,c)->{
            lblTime.setText(c);
        });
        Thread t1 = new Thread(a);
        t1.setDaemon(true);
        t1.start();

    }


    public void showDahboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AHOME,pane);
    }

    public void showEmployeeFrameOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void showItemFrameOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEMS,pane);
    }

    public void showCustomerFrameOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,pane);
    }

    public void showSupplierFrameOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER,pane);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        this.userService = ServiceFactory.getInstance().getService(ServiceTypes.USER);
        UserDTO u2 = new UserDTO(u1.getUsername(),u1.getType(),u1.getPassword(),"No", u1.getHint());
        boolean updateuser = userService.update(u2);
        if (updateuser){
            Navigation.navigate(Routes.SIGNIN, pane);
        }
    }

    public void btndashboardOnMouseExitAction(MouseEvent mouseEvent) {
        btnDashboard.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgDashboard);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();

    }

    public void btnDashboradOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgDashboard);
        transition.setToX(-70);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnDashboard.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnEmployeeOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgEmployee);
        transition.setToX(-60);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnEmployee.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnEmployeeOnMouseExitAction(MouseEvent mouseEvent) {
        btnEmployee.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgEmployee);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }

    public void btnItemOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgItem);
        transition.setToX(-60);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnItem.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnItemOnMouseExitAction(MouseEvent mouseEvent) {
        btnItem.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgItem);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }

    public void btnCustomerOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgCustomer);
        transition.setToX(-60);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnCustomer.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnCustomerOnMouseExitAction(MouseEvent mouseEvent) {
        btnCustomer.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgCustomer);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }

    public void btnSupplierOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgSupplier);
        transition.setToX(-60);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnSupplier.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnSupplierOnMouseExitAction(MouseEvent mouseEvent) {
        btnSupplier.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgSupplier);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }

    public void btnLogoutOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgLogout);
        transition.setToX(-60);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnLogout.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnLogoutOnMouseExitAction(MouseEvent mouseEvent) {
        btnLogout.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgLogout);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }
}
