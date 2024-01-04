package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends SuperDAO {
    public ArrayList<String> loadVehicleIds() throws SQLException, ClassNotFoundException;

    public String generateNewDeliveryId();

    public boolean saveDelivery(Delivery de1) throws SQLException;

    boolean saveHaveDelivery(Delivery de1) throws SQLException;
}
