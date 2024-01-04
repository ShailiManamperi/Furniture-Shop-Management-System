package lk.ijse.shaili.system.Service.custom;



import lk.ijse.shaili.system.Service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryService extends SuperService {
    public ArrayList<String> loadVehicleId() throws SQLException, ClassNotFoundException;

    public String findNewDeliveryId();
}
