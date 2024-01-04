package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.CrudDAO;
import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemDAO extends SuperDAO {
    Item save(Item entity) throws ConstraintViolationException;

    Item update(Item entity) throws ConstraintViolationException;

    boolean deleteByPk(String pk) throws ConstraintViolationException;

    List<Item> findAll();

    Optional<Item> findByPk(String pk);

    boolean existByPk(String pk);

    long count();

    public List<Item> searchByText(String text);

    public String findNewItemId();

    public List<Item> getAllItems();

    public  boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException;

    boolean updateLoadQty(ArrayList<Detail> details) throws SQLException,ClassNotFoundException;

    public  boolean saveOrderDetails(ArrayList<CartDetail> cartDetails, PlaceOrder placeOrder) throws SQLException, ClassNotFoundException;

    public  ArrayList<String> loadItemIds() throws SQLException, ClassNotFoundException;

    public BestItem findBestItem();
}
