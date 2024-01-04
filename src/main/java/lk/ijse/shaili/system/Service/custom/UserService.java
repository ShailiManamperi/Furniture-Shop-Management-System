package lk.ijse.shaili.system.Service.custom;



import lk.ijse.shaili.system.Dto.UserDTO;
import lk.ijse.shaili.system.Service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {
    public UserDTO search(String code) throws SQLException, ClassNotFoundException;

    public boolean save (UserDTO userDTO) throws SQLException, ClassNotFoundException;

    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
