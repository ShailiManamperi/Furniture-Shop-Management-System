package lk.ijse.shaili.system.Dao.custom;

import lk.ijse.shaili.system.Dao.SuperDAO;
import lk.ijse.shaili.system.Entity.Invoice;

public interface InvoiceDAO extends SuperDAO {
    Invoice saveInvoice(Invoice entity);

    Invoice savehaveInvoice(Invoice entity);

    Invoice searchInvoice(String id);

    long count();

    boolean deleteInvoice(String id);
}
