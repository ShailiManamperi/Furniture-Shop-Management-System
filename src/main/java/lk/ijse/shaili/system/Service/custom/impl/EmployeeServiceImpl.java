package lk.ijse.shaili.system.Service.custom.impl;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.custom.employeeDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.employeeDTO;
import lk.ijse.shaili.system.Service.custom.EmployeeService;
import lk.ijse.shaili.system.Service.exception.*;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {

    private final Connection connection;
    private final Converter converter;
    private final employeeDAO emDAO;



    public EmployeeServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        emDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE);
        converter = new Converter();

    }

    @Override
    public employeeDTO saveEmployee(employeeDTO eDTO) throws DuplicateException {
        if (emDAO.existByPk(eDTO.getEid())) {
            throw new DuplicateException("This Employee id is already added!");
        } else {
            emDAO.save(converter.toEmployee(eDTO));
            return eDTO;
        }
    }

    @Override
    public employeeDTO updateEmployee(employeeDTO eDTO) throws NotFoundException {
        if (!emDAO.existByPk(eDTO.getEid())) {
            throw new NotFoundException("Member not found!");
        } else {
            emDAO.update(converter.toEmployee(eDTO));
            return eDTO;
        }
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (!emDAO.existByPk(employeeId)) {
            throw new NotFoundException("Member not found!");
        }
        return emDAO.deleteByPk(employeeId);
    }

    @Override
    public employeeDTO searchEmployee(String employeeId, String type) throws NotFoundException {
        if (!emDAO.existByPk(employeeId)){
            throw new NotFoundException("Employee is Not Found!");
        }
        return converter.fromEmployee(emDAO.findEmployee(employeeId,type));
    }

    @Override
    public String generateNewEmployeeId() throws SQLException {
        String newEmployeeId = emDAO.findNewEmployeeId();
        return newEmployeeId;
    }
}
