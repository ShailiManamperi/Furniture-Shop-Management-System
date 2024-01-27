package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Service.custom.UserService;
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;

import java.io.IOException;
import java.time.LocalTime;

public class LoginFrameController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtInUsername;

    @FXML
    private JFXPasswordField txtInPassword;

    @FXML
    private JFXButton btnLogIn;

    @FXML
    private Label lblForget;

    @FXML
    private Label lblHint;

    @FXML
    private JFXTextField txtShowPassword;

    @FXML
    private ImageView imgShowPassword;

    @FXML
    private ImageView imgHideEye;

    @FXML
    private JFXTextField txtUpUsername;

    @FXML
    private JFXPasswordField txtUpPassword;

    @FXML
    private JFXTextField txtPsHint;

    @FXML
    private JFXComboBox cmbUserType;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private AnchorPane slider;

    @FXML
    private Label lblGreeting;

    @FXML
    private Label lblwelcome;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblCreate;

    @FXML
    private JFXButton btncreate;

    @FXML
    private JFXButton btnlogin;

    private UserService userService;

    public static UserDTO u1;


    public void initialize(){
        setWelcome();
        cmbUserType.getItems().addAll("Admin", "Cashier");
        cmbUserType.setPromptText("Select Login Type");
        imgHideEye.setVisible(false);
        txtShowPassword.setVisible(false);
        lblwelcome.setVisible(false);
        lblLogin.setVisible(false);
        btnlogin.setVisible(false);

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


    public void LoginUserOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN,pane);

    }

    public void getPasswordHintOnMoueClicked(MouseEvent mouseEvent) {
    }

    public void SHowPasswordOnMouseClicked(MouseEvent mouseEvent) {
        String password = txtInPassword.getText();
        txtShowPassword.setVisible(true);
        txtInPassword.setVisible(false);
        txtShowPassword.setText(password);
        imgHideEye.setVisible(true);
        imgShowPassword.setVisible(false);
    }

    public void HidePasswordOnMOuseClicked(MouseEvent mouseEvent) {
        String password = txtShowPassword.getText();
        txtInPassword.setVisible(true);
        txtShowPassword.setVisible(false);
        txtInPassword.setText(password);
        imgShowPassword.setVisible(true);
        imgHideEye.setVisible(false);
    }

    public void RegisterUserOnAction(ActionEvent actionEvent) {
    }

    public void ShowRegisterFormOnAction(ActionEvent actionEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1.5));
        slide.setNode(slider);

        slide.setToX(-480);
        slide.play();
        lblwelcome.setVisible(true);
        lblLogin.setVisible(true);
        btnlogin.setVisible(true);
        lblGreeting.setVisible(false);
        lblCreate.setVisible(false);
        btncreate.setVisible(false);
    }

    public void ShowLoginFormOnAction(ActionEvent actionEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1.5));
        slide.setNode(slider);

        slide.setToX(0);
        slide.play();
        lblwelcome.setVisible(false);
        lblLogin.setVisible(false);
        btnlogin.setVisible(false);
        lblGreeting.setVisible(true);
        lblCreate.setVisible(true);
        btncreate.setVisible(true);
    }
}

