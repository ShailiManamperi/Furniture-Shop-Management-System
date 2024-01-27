package lk.ijse.shaili.system.Service.custom.impl;


import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Dao.custom.InvoiceDAO;
import lk.ijse.shaili.system.Dao.custom.ItemDAO;
import lk.ijse.shaili.system.Dao.custom.SupplierOrderDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.InvoiceDTO;
import lk.ijse.shaili.system.Dto.SupplierOrderDTO;
import lk.ijse.shaili.system.Entity.Invoice;
import lk.ijse.shaili.system.Entity.Supplier_oder;
import lk.ijse.shaili.system.Service.custom.SupplierOrderService;
import lk.ijse.shaili.system.Service.exception.DuplicateException;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierOrderServiceImpl implements SupplierOrderService {
    private final Converter converter;
    private final Connection connection;
    private final SupplierOrderDAO supplierOrderDAO;
    private final ItemDAO itemDAO;
    private final InvoiceDAO invoiceDAO;

    public SupplierOrderServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        supplierOrderDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.SUPPLIERORDER);
        itemDAO = DaoFactory.getInstance().getDAO(connection,DaoTypes.ITEM);
        invoiceDAO= DaoFactory.getInstance().getDAO(connection,DaoTypes.INVOICE);
    }

    @Override
    public SupplierOrderDTO saveSupplierOrder(SupplierOrderDTO supplierOrderDTO) throws DuplicateException {
        supplierOrderDAO.saveOrder(converter.toSupplierorder(supplierOrderDTO));
        return supplierOrderDTO;
    }

    @Override
    public String generateNewId() throws SQLException {
        String newLoadId = supplierOrderDAO.findNewLoadId();
        return newLoadId;
    }

    @Override
    public boolean PlaceLoad(SupplierOrderDTO supplierOrderDTO, InvoiceDTO invoiceDTO) throws ClassNotFoundException, SQLException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            System.out.println("befor "+supplierOrderDTO);
            Supplier_oder supplier_oder = converter.toSupplierorder(supplierOrderDTO);
            System.out.println("load "+supplier_oder);
            Supplier_oder supplier_oder1 = supplierOrderDAO.saveOrder(supplier_oder);
            if (supplier_oder1 != null) {
                boolean updateLoadQty = itemDAO.updateLoadQty(supplier_oder.getDetails());
                if (updateLoadQty) {
                    Invoice invoice = converter.toInvoice(invoiceDTO);
                    String status = supplierOrderDTO.getStatus();
                    Invoice invoice1 = null;
                    Invoice invoice2 = null;
                    if (status.equals("Cash")){
                       invoice1 = invoiceDAO.saveInvoice(invoice);
                    }else {
                       invoice2 = invoiceDAO.savehaveInvoice(invoice);
                    }
                    if ((invoice1 != null) || (invoice2!= null)) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }
}
