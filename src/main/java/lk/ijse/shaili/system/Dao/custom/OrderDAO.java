package lk.ijse.shaili.system.Dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDAO extends SuperDAO {
    Order save(Order entity) throws ConstraintViolationException;

    Order update(Order entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Order> findAll();

    Optional<Order> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

    public ObservableList<Order> getAllOrderByCId(String id);

    public String findNewOrderId();

    public Integer[] geOrderValueMonths() throws SQLException;

    public  String findTodaySales();

    String findTodaySaleCount();
}
