package lk.ijse.shaili.system.Dao.custom;


import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Entity.Supplier_oder;

public interface SupplierOrderDAO extends SuperDAO {
    public String findNewLoadId();

    Supplier_oder saveOrder(Supplier_oder entity);

}
