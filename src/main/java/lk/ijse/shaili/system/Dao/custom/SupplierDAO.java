package lk.ijse.shaili.system.Dao.custom;
//
//import lk.ijse.system.dao.CrudDAO;
//import lk.ijse.system.entity.Supplier;

import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.Supplier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SupplierDAO extends SuperDAO {
    Supplier save(Supplier entity) throws ConstraintViolationException;

    Supplier update(Supplier entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Supplier> findAll();

    Optional<Supplier> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

     Supplier searchsupplier(String id);

     List<Supplier> searchByText(String text);

    String findNewSupplierId();

    List<String> findCompanies() throws SQLException;
}
