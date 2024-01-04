package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.InvoiceDTO;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

public interface InvoiceService extends SuperService {
    InvoiceDTO savehaveInvoice(InvoiceDTO invoiceDTO) throws DuplicateException;

    InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO) throws  DuplicateException;

    boolean deleteInvoice(String id) throws NotFoundException;

    long countInvoice() ;

    InvoiceDTO searchInvoie(String id) throws NotFoundException;
}
