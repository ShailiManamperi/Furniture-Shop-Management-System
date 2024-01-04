package lk.ijse.shaili.system.Service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Entity.Order;
import lk.ijse.shaili.system.Service.custom.OrderService;
import lk.ijse.shaili.system.Service.exception.NotFoundException;
import lk.ijse.shaili.system.Service.util.Converter;
import lk.ijse.shaili.system.Dao.custom.OrderDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    private final Connection connection;
    private final Converter converter;
    private final OrderDAO orderDAO;

    public OrderServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        orderDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.ORDER);
        converter = new Converter();
    }

    @Override
    public ObservableList<Order> getAllOrders(String Cid)  {
        return orderDAO.getAllOrderByCId(Cid);
    }

    @Override
    public String generateNewOrderId() throws NotFoundException {
        String newOrderId = orderDAO.findNewOrderId();
        return newOrderId;
    }

    @Override
    public Integer[] geOrderValueMonths() throws SQLException {
        return orderDAO.geOrderValueMonths();
    }

    @Override
    public String findTodaySales() {
        return orderDAO.findTodaySales();
    }

    @Override
    public String findTodaySalesCount() {
        return orderDAO.findTodaySaleCount();
    }
}
