package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class AttendanceController {
    public Label lbltitle;
    public TableView tblattend;
    public TableColumn colempid;
    public TableColumn colattened;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXRadioButton rbFull;
    public JFXRadioButton rbhalf;
    public JFXRadioButton rbabbsent;

    public void attendOnMouseClicked(MouseEvent mouseEvent) {
    }
}
