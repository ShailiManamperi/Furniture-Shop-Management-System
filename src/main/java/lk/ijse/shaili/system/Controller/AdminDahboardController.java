package lk.ijse.shaili.system.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.shaili.system.Dto.BestCustomerDTO;
import lk.ijse.shaili.system.Dto.BestItemDTO;
import lk.ijse.shaili.system.Dto.CustomerDTO;
import lk.ijse.shaili.system.Entity.Item;
import lk.ijse.shaili.system.Service.ServiceFactory;
import lk.ijse.shaili.system.Service.ServiceTypes;
import lk.ijse.shaili.system.Service.custom.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;

public class AdminDahboardController {

    public AnchorPane frame;
    public AnchorPane customerPane;
    public AnchorPane customer1;
    public Label lblCustname;
    public Label lblcustodercount;
    public AnchorPane customer2;
    public Label lblItemname;
    public Label lblitemOrdercount;
    public AnchorPane customer3;
    public Label lblprice;
    public Label lblordercount;
    public AnchorPane chartPane;
    public BarChart bargraph;

    private CustomerService customerService;
    private InvoiceService invoiceService;
    private OrderService orderService;
    private ItemService itemService;

    public void initialize() throws SQLException {
        this.customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
        this.invoiceService = ServiceFactory.getInstance().getService(ServiceTypes.INVOICE);
        this.orderService = ServiceFactory.getInstance().getService(ServiceTypes.ORDER);
        this.itemService = ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
//        loadbest();
//        loadbarchart();
    }

    private void loadbarchart() throws SQLException {
        Integer[] data = orderService.geOrderValueMonths();
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("No. of Order");
        series.getData().add(new XYChart.Data("JAN", data[0]));
        series.getData().add(new XYChart.Data("FEB", data[1]));
        series.getData().add(new XYChart.Data("MAR", data[2]));
        series.getData().add(new XYChart.Data("APR", data[3]));
        series.getData().add(new XYChart.Data("MAY", data[4]));
        series.getData().add(new XYChart.Data("JUN", data[5]));
        series.getData().add(new XYChart.Data("JUL", data[6]));
        series.getData().add(new XYChart.Data("AUG", data[7]));
        series.getData().add(new XYChart.Data("SEP", data[8]));
        series.getData().add(new XYChart.Data("OCT", data[9]));
        series.getData().add(new XYChart.Data("NOV", data[10]));
        series.getData().add(new XYChart.Data("DEC", data[11]));
        bargraph.getData().addAll(series);
    }

    private void loadbest() {
        BestCustomerDTO bestCustomer = customerService.findBestCustomer();
        CustomerDTO customerDTO = customerService.searchCustomer(bestCustomer.getCid(), "C_id");
        lblCustname.setText(customerDTO.getName());
        lblcustodercount.setText(String.valueOf(bestCustomer.getCount()));

        BestItemDTO bestItem = itemService.findBestItem();
        Optional<Item> item = itemService.searchItem(bestItem.getCode());
        lblItemname.setText(item.get().getName());
        lblitemOrdercount.setText(String.valueOf(bestItem.getCount()));

        String todaySales = orderService.findTodaySales();
        String todaySalesCount = orderService.findTodaySalesCount();
        lblprice.setText(todaySales);
        lblordercount.setText(todaySalesCount);
    }
}
