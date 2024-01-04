package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.InvoiceDTO;
import lk.ijse.shaili.system.Dto.SupplierOrderDTO;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.DuplicateException;

import java.sql.SQLException;

public interface SupplierOrderService extends SuperService {
    public SupplierOrderDTO saveSupplierOrder(SupplierOrderDTO supplierOrderDTO) throws DuplicateException;

    public String generateNewId() throws SQLException;

    boolean PlaceLoad(SupplierOrderDTO supplierOrderDTO, InvoiceDTO invoiceDTO) throws ClassNotFoundException,SQLException;

}
