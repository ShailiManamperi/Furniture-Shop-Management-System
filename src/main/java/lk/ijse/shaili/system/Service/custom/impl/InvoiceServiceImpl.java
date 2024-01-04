package lk.ijse.shaili.system.Service.custom.impl;


import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.InvoiceDTO;
import lk.ijse.shaili.system.Entity.Invoice;
import lk.ijse.shaili.system.Service.custom.InvoiceService;
import lk.ijse.shaili.system.Service.exception.*;
import lk.ijse.shaili.system.Service.util.Converter;
import lk.ijse.shaili.system.Dao.custom.InvoiceDAO;

import java.sql.Connection;

public class InvoiceServiceImpl implements InvoiceService {

    private final Converter converter;
    private final Connection connection;
    private final InvoiceDAO invoiceDAO;

    public InvoiceServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        invoiceDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.INVOICE);

    }

    @Override
    public InvoiceDTO savehaveInvoice(InvoiceDTO invoiceDTO) throws DuplicateException {
        Invoice invoice = invoiceDAO.savehaveInvoice(converter.toInvoice(invoiceDTO));
        if (invoice == null){
            throw new DuplicateException("This invoice cannot be added!");
        }
        return converter.fromInvoice(invoice);
    }

    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO) throws DuplicateException {
        Invoice invoice = invoiceDAO.saveInvoice(converter.toInvoice(invoiceDTO));
        if (invoice == null){
            throw new DuplicateException("This invoice cannot be added!");
        }
        return converter.fromInvoice(invoice);
    }

    @Override
    public boolean deleteInvoice(String id) throws NotFoundException {
        if (invoiceDAO.searchInvoice(id) == null){
            throw new NotFoundException("invoice is not found");
        }
        return invoiceDAO.deleteInvoice(id);
    }

    @Override
    public long countInvoice() {
        return invoiceDAO.count();
    }

    @Override
    public InvoiceDTO searchInvoie(String id) throws NotFoundException {
        if (invoiceDAO.searchInvoice(id) == null){
            throw new NotFoundException("this invoice is not found");
        }
        return converter.fromInvoice(invoiceDAO.searchInvoice(id));
    }
}
