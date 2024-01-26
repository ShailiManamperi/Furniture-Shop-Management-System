package lk.ijse.shaili.system.Service.custom;


import lk.ijse.shaili.system.Dto.SupplierDTO;
import lk.ijse.shaili.system.Entity.Supplier;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SupplierService extends SuperService {
     Optional<Supplier> searchSupplierByID(String id);

     SupplierDTO searchSupplier(String text);

     SupplierDTO saveSupplier(SupplierDTO supplierDTO) throws DuplicateException;

     SupplierDTO updatSupplier(SupplierDTO supplierDTO) throws NotFoundException;

     List<SupplierDTO> searchSupplierList(String text) throws NotFoundException;

     boolean deleteSupplier(String id) throws  NotFoundException;

     String generateNewSupplierId() throws SQLException;

     boolean searchDuplicate(String id) throws NotFoundException;

     List<SupplierDTO> getAllSupplier();

     Optional<SupplierDTO> searchSupplier1(String id);

     List<String> getSuppliers() throws SQLException;
}
