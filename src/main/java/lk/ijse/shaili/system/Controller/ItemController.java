package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ItemController {
    public AnchorPane frame;
    public TableView tblItems;
    public TableColumn colId;
    public TableColumn colname;
    public TableColumn colQty;
    public JFXTextField txtid;
    public JFXTextField txtname;
    public JFXComboBox cmbtype;
    public JFXTextField txtsupplier;
    public JFXTextField txtprice;
    public JFXTextField txtprice1;
    public JFXTextField txtqty;
    public ImageView imghome;
    public JFXTextField txtSearch;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnNew;
    public JFXButton btnnewload;

    public void itemsdetailOnMouseClikced(MouseEvent mouseEvent) {
    }

    public void HomePageOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void addItemOnAction(ActionEvent actionEvent) {
    }

    public void UpdateItemOnAction(ActionEvent actionEvent) {
    }

    public void DeleteItemOnAction(ActionEvent actionEvent) {
    }

    public void ClearItemOnAction(ActionEvent actionEvent) {
    }

    public void newItemIdOnAction(ActionEvent actionEvent) {
    }

    public void addnewitemloadframeOnAction(ActionEvent actionEvent) {
    }
}
