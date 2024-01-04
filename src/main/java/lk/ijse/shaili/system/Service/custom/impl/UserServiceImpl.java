package lk.ijse.shaili.system.Service.custom.impl;


import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Dao.custom.UserDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Entity.User;
import lk.ijse.shaili.system.Service.custom.UserService;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final Converter converter;
    private final Connection connection;
    private final UserDAO userDAO;

    public UserServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        userDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.USER);
    }

    @Override
    public UserDTO search(String code) throws SQLException, ClassNotFoundException {
        User search = userDAO.search(code);
        return converter.fromUser(search);
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        boolean save = userDAO.save(converter.toUser(userDTO));
        return save;
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        boolean updateuser = userDAO.updateuser(converter.toUser(userDTO));
        return updateuser;
    }
}
