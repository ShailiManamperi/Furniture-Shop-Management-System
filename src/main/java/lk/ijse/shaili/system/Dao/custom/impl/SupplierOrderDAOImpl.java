package lk.ijse.shaili.system.Dao.custom.impl;


import lk.ijse.shaili.system.Dao.custom.SupplierOrderDAO;
import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Dao.util.DBUtil;
import lk.ijse.shaili.system.Entity.Detail;
import lk.ijse.shaili.system.Entity.Supplier_oder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {

    private final Connection connection;

    public SupplierOrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findNewLoadId() {
        String loadid = null;
        try {
            String sql = "SELECT so_id FROM supplier_order ORDER BY so_id DESC LIMIT 1";
            ResultSet result = DBUtil.executeQuery(sql);
            if (!result.next()) {
                loadid = generateNextLoadId(null);
            }
            loadid = generateNextLoadId(result.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadid;
    }

    private String generateNextLoadId(String currentLoadId) {
        if (currentLoadId == null) {
            return "L00001";
        }else{
            String[] split = currentLoadId.split("L");
            int id = Integer.parseInt(split[1]);
            id++;
            String newId = String.format("L%05d", id);
            return newId;
        }
    }

    @Override
    public Supplier_oder saveOrder(Supplier_oder entity) {
        System.out.println(entity);
        try {
            if(DBUtil.executeUpdate("insert into supplier_order values(?,?,?,?,?)",
            entity.getSoi_id(),entity.getDate(),entity.getSup_id(),entity.getAmount(),entity.getStatus())){
                boolean orderDetail = saveOrderDetail(entity.getDetails(), entity);
                if (orderDetail){
                    return entity;
                }
            }
            throw new SQLException("Failed to save the items");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        }
    }

    private boolean saveOrderDetail(ArrayList<Detail> details, Supplier_oder entity) throws SQLException {
        for (Detail detail : details) {
            if (!save(detail,entity)) {
                return false;
            }
        }
        return true;
    }

    private boolean save(Detail detail, Supplier_oder entity) throws SQLException {
        String sql ="INSERT INTO supplier_order_detail VALUES (?,?,?,?)";
        return DBUtil.executeUpdate(sql,entity.getSoi_id(),detail.getCode(),detail.getQty(),detail.getPrice());
    }
}
