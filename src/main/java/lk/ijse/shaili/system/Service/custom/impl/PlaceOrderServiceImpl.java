package lk.ijse.shaili.system.Service.custom.impl;

import lk.ijse.shaili.system.Dao.custom.OrderDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.DeliveryDTO;
import lk.ijse.shaili.system.Dto.OrderDTO;
import lk.ijse.shaili.system.Dto.PlaceOrderDTO;
import lk.ijse.shaili.system.Entity.Order;
import lk.ijse.shaili.system.Service.custom.PlaceOrderService;
import lk.ijse.shaili.system.Dao.*;
import lk.ijse.shaili.system.Service.util.Converter;
import lk.ijse.shaili.system.Dao.custom.ItemDAO;
import lk.ijse.shaili.system.Dao.custom.DeliveryDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private final Connection connection;
    private final Converter converter;
    private final OrderDAO orderDAO;
    private final ItemDAO itemDAO;
    private final DeliveryDAO deliveryDAO;

    public PlaceOrderServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        orderDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.ORDER);
        itemDAO = DaoFactory.getInstance().getDAO(connection,DaoTypes.ITEM);
        deliveryDAO = DaoFactory.getInstance().getDAO(connection,DaoTypes.DELIVERY);
        converter = new Converter();
    }
    @Override
    public boolean PlaceOrderWithDelivery(PlaceOrderDTO placeOrder, DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            Order order = converter.toOrder(new OrderDTO(placeOrder.getOid(), placeOrder.getDate(), placeOrder.getCid(), placeOrder.getStatus(), placeOrder.getPrice()));
            Order save = orderDAO.save(order);
            if (save != null) {
                boolean isUpdated = itemDAO.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean issaved = itemDAO.saveOrderDetails(placeOrder.getOrderDetails(), converter.toPlaceOrder(placeOrder));
                    if (issaved) {
                        boolean saveDelivery = deliveryDAO.saveHaveDelivery(converter.toDelivery(deliveryDTO));
                        if (saveDelivery) {
                            DBConnection.getDbConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean PlaceOrderWithoutDelivery(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            Order order = converter.toOrder(new OrderDTO(placeOrder.getOid(), placeOrder.getDate(), placeOrder.getCid(), placeOrder.getStatus(), placeOrder.getPrice()));
            Order save = orderDAO.save(order);
            if (save != null) {
                boolean isUpdated = itemDAO.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean issaved = itemDAO.saveOrderDetails(placeOrder.getOrderDetails(), converter.toPlaceOrder(placeOrder));
                    if (issaved) {
                        System.out.println("saved all");
                        DBConnection.getDbConnection().getConnection().commit();
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return true;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }
}
