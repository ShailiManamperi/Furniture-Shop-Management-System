package lk.ijse.shaili.system.Dao.custom;


import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Entity.User;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    public boolean updateuser(User u1) throws SQLException, ClassNotFoundException;

    public  boolean save(User u1) throws SQLException, ClassNotFoundException;

    public  User search(String code) throws SQLException, ClassNotFoundException;
}
