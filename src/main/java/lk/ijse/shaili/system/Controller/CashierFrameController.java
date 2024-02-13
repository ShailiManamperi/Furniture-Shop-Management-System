package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.UserService;
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;
import lk.ijse.shaili.system.Util.Task.TimeTask;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

public class CashierFrameController {
    public AnchorPane pane;
    public AnchorPane frame;
    public JFXButton btnOrder;
    public JFXButton btnLogout;
    public ImageView imgOrder;
    public ImageView imgLogout;
    public Label lblGreeting;
    public AnchorPane pane1;
    public Label lblName;
    public Label lblTime;

    UserDTO u1 = LoginFrameController.u1;
    public UserService userService;

    public void initialize() throws IOException {
        setWelcome();
        setTime();
        Parent load = FXMLLoader.load(getClass().getResource("/View/main/orderFrame.fxml"));
        pane1.getChildren().clear();
        pane1.getChildren().add(load);
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


    public void logoutOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        this.userService = ServiceFactory.getInstance().getService(ServiceTypes.USER);
        UserDTO u2 = new UserDTO(u1.getUsername(),u1.getType(),u1.getPassword(),"No", u1.getHint());
        boolean updateuser = userService.update(u2);
        if (updateuser){
            Navigation.navigate(Routes.SIGNIN, pane);
        }
    }

    public void btndashboardOnMouseExitAction(MouseEvent mouseEvent) {
        btnOrder.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgOrder);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }

    public void showDahboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER,frame);
    }

    public void btnDashboradOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgOrder);
        transition.setToX(-70);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        btnOrder.setContentDisplay(ContentDisplay.CENTER);
    }

    public void btnLogoutOnMouseEnteredAction(MouseEvent mouseEvent) {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgLogout);
        transition.setToX(-70);
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
