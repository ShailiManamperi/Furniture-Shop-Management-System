package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.employeeDTO;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

import java.sql.SQLException;

public interface EmployeeService extends SuperService {
    public employeeDTO saveEmployee(employeeDTO eDTO) throws DuplicateException;

    public employeeDTO updateEmployee(employeeDTO eDTO) throws NotFoundException;

    public boolean deleteEmployee(String employeeId) throws NotFoundException, InUseException, SQLException, ClassNotFoundException;

    public employeeDTO searchEmployee(String employeeId, String type) throws NotFoundException;

    public String generateNewEmployeeId() throws SQLException;

}
