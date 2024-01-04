package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface employeeDAO extends SuperDAO {
    Employee save(Employee entity) throws ConstraintViolationException;

    Employee update(Employee entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Employee> findAll();

    Optional<Employee> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

    public List<Employee> searchByText(String text);

    public Employee findEmployee(String id, String type);

    public String findNewEmployeeId();

}
