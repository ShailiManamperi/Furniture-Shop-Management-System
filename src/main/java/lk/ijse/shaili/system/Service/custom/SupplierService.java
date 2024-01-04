package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.SupplierDTO;
import lk.ijse.shaili.system.Entity.Supplier;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SupplierService extends SuperService {
    public Optional<Supplier> searchSupplierByID(String id);

    public SupplierDTO searchSupplier(String text);

    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) throws DuplicateException;

    public SupplierDTO updatSupplier(SupplierDTO supplierDTO) throws NotFoundException;

    public List<SupplierDTO> searchSupplierList(String text) throws NotFoundException;

    public boolean deleteSupplier(String id) throws  NotFoundException;

    public String generateNewSupplierId() throws SQLException;

    public boolean searchDuplicate(String id) throws NotFoundException;

    public List<SupplierDTO> getAllSupplier();

    public Optional<SupplierDTO> searchSupplier1(String id);
}
