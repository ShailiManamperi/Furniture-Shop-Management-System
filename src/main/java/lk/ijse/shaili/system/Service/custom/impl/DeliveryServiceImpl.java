package lk.ijse.shaili.system.Service.custom.impl;

import lk.ijse.shaili.system.Dao.custom.DeliveryDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Service.custom.DeliveryService;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;

public class DeliveryServiceImpl implements DeliveryService {
    private final Converter converter;
    private final Connection connection;
    private final DeliveryDAO deliveryDAO;

    public DeliveryServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        deliveryDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.DELIVERY);

    }
    @Override
    public ArrayList<String> loadVehicleId() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleidList = deliveryDAO.loadVehicleIds();
        return vehicleidList;
    }

    @Override
    public String findNewDeliveryId() {
        String newDeliveryId = deliveryDAO.generateNewDeliveryId();
        return newDeliveryId;
    }
}
