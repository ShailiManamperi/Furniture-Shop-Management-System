package lk.ijse.shaili.system.Service.custom;


import javafx.collections.ObservableList;
import lk.ijse.shaili.system.Entity.Order;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.NotFoundException;

import java.sql.SQLException;

public interface OrderService extends SuperService {
    public ObservableList<Order> getAllOrders(String Cid) ;

    public String generateNewOrderId() throws NotFoundException;

    public Integer[] geOrderValueMonths() throws SQLException;

    public String findTodaySales();

    String findTodaySalesCount();

}
