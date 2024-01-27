package lk.ijse.shaili.system.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shaili.system.Dtm.ItemTM;
import lk.ijse.shaili.system.Dto.ItemDTO;
import lk.ijse.shaili.system.Dto.SupplierDTO;
import lk.ijse.shaili.system.Entity.Item;
import lk.ijse.shaili.system.Entity.Supplier;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.ItemService;
import lk.ijse.shaili.system.Service.custom.SupplierService;
import lk.ijse.shaili.system.Util.Navigation;
import lk.ijse.shaili.system.Util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemController {
    public AnchorPane frame;
    public TableView<ItemTM> tblItems;
    public TableColumn<ItemTM, String> colId;
    public TableColumn<ItemTM, String> colname;
    public TableColumn<ItemTM, Integer> colQty;
    public JFXTextField txtid;
    public JFXTextField txtname;
    public JFXComboBox<String> cmbtype;
    public JFXComboBox<String> cmbsupplier;
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

    public ItemService itemService;
    public SupplierService supplierService;
    public ItemTM selectedItem;

    public void initialize(){
        String[] type = {"Melamine","Steel","Aliminium","Wooden","Plastic","Cushion","Metttress","Iron"};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        cmbtype.setItems(list);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        this.itemService = ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
        this.supplierService =ServiceFactory.getInstance().getService(ServiceTypes.SUPPLIER);
        List<ItemDTO> allItems = itemService.getAllItems();

        List<ItemTM> itemTmList = allItems.stream().map(item ->
                new ItemTM(item.getCode(), item.getName(), item.getQty())).collect(Collectors.toList());
        tblItems.setItems(FXCollections.observableArrayList(itemTmList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) -> {
            if (!Objects.equals(pre, curr)) {
                tblItems.getItems().clear();
                this.itemService = ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
                List<ItemTM> searchResult = itemService.searchItemList(curr).stream().map(item ->
                        new ItemTM(item.getCode(), item.getName(), item.getQty())).collect(Collectors.toList());
                tblItems.setItems(FXCollections.observableArrayList(searchResult));
            }
        });

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr != pre || curr != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        });
        try {
            List<String> suppliers = supplierService.getSuppliers();
            ObservableList<String> list1 = FXCollections.observableArrayList(suppliers);
            cmbsupplier.setItems(list1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private ItemDTO makeObject() throws SQLException {
        String id = txtid.getText();
        String name = txtname.getText();
        double getPrice = Double.parseDouble(txtprice.getText());
        double sellPrice = Double.parseDouble(txtprice1.getText());
        int qty = Integer.parseInt(txtqty.getText());
        String type = cmbtype.getSelectionModel().getSelectedItem().toString();
        String supplier = cmbsupplier.getSelectionModel().getSelectedItem().toString();
        SupplierDTO supplierDTO = supplierService.searchSupplier(supplier);
        ItemDTO itemDTO = new ItemDTO(id, name, type, getPrice, sellPrice, qty, supplierDTO.getSid());
        return itemDTO ;
    }

    public void itemsdetailOnMouseClikced(MouseEvent mouseEvent) {
        selectedItem = tblItems.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getCode());
        Optional<Item> item = itemService.searchItem(selectedItem.getCode());
        System.out.println(item);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        txtid.setText(item.get().getCode());
        txtname.setText(item.get().getName());
        txtprice.setText(String.valueOf(item.get().getGet_price()));
        txtprice1.setText(String.valueOf(item.get().getSell_price()));
        txtqty.setText(String.valueOf(item.get().getQty()));
        String supid = item.get().getSupid();
        Optional<Supplier> supplier = supplierService.searchSupplierByID(supid);
        cmbsupplier.setValue(supplier.get().getCompany());
        cmbtype.setValue(item.get().getType());
    }

    public void HomePageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.AHOME,frame);
    }

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException {
        ItemDTO itemDTO = makeObject();
        boolean duplicate = itemService.searchDuplicate(itemDTO.getCode());
        if (duplicate){
            new Alert(Alert.AlertType.WARNING,"This Item id is already added!").show();
        }else {
            ItemDTO itemDTO1 = itemService.saveItem(itemDTO);
            if (itemDTO1 != null){
                tblItems.getItems().add(new ItemTM(itemDTO1.getCode(),itemDTO1.getName(),itemDTO1.getQty()));
                new Alert(Alert.AlertType.CONFIRMATION,"This item added successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"This item cannot added !").show();
            }
        }

    }

    public void UpdateItemOnAction(ActionEvent actionEvent) throws SQLException {
        ItemDTO itemDTO = makeObject();
        String text = txtid.getText();
        if (text.equals(selectedItem.getCode())){
            ItemDTO itemDTO1 = itemService.updatItem(itemDTO);
            if (itemDTO1 == null){
                new Alert(Alert.AlertType.ERROR,"This Customer is Not Update!", ButtonType.OK).show();
            }else {
                ClearItemOnAction(actionEvent);
                new Alert(Alert.AlertType.CONFIRMATION,"This customer Detail is Update Sucessfully!",ButtonType.CLOSE).show();
            }
        }else{
            txtid.setText(selectedItem.getCode());
            new Alert(Alert.AlertType.WARNING,"You cannot Update item id").show();
        }
    }

    public void DeleteItemOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete this Item ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean deleteCustomer = itemService.deleteItem(id);
            if (deleteCustomer) {
                tblItems.getItems().removeAll(tblItems.getSelectionModel().getSelectedItem());
                new Alert(Alert.AlertType.INFORMATION, "Item delete successful").show();
                ClearItemOnAction(actionEvent);
            }
        }
    }

    public void ClearItemOnAction(ActionEvent actionEvent) {
        txtid.clear();
        txtname.clear();
        txtprice.clear();
        txtqty.clear();
        txtprice1.clear();
        txtSearch.clear();
        cmbtype.setValue("Select Type");
        cmbsupplier.setValue("Select Supplier");
    }

    public void newItemIdOnAction(ActionEvent actionEvent) {
        try {
            String itemId = itemService.generateNewItemId();
            txtid.setText(itemId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Cannot Generate a Item id.", ButtonType.CLOSE).show();
        }
    }

    public void addnewitemloadframeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/main/newItemLoadFrame.fxml");
        AnchorPane container = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(container));
        stage.centerOnScreen();
        stage.show();
    }
}
