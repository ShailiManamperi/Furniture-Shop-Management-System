package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.Customer;
import lk.ijse.shaili.system.Entity.BestCustomer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends SuperDAO {
    Customer save(Customer entity) throws ConstraintViolationException;

    Customer update(Customer entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Customer> findAll();

    Optional<Customer> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

    public Customer findCustomer(String id, String type);

    public String findNewCustomerId();

    public BestCustomer findBestCustomer();
}
