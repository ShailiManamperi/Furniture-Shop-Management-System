package lk.ijse.shaili.system.Service.custom.impl;


import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.SupplierDTO;
import lk.ijse.shaili.system.Entity.Supplier;
import lk.ijse.shaili.system.Service.custom.SupplierService;
import lk.ijse.shaili.system.Service.exception.*;
import lk.ijse.shaili.system.Service.util.Converter;
import lk.ijse.shaili.system.Dao.custom.SupplierDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    private final Converter converter;
    private final Connection connection;
    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        supplierDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.SUPPLIER);
    }
    @Override
    public Optional<Supplier> searchSupplierByID(String id) {
        if (!supplierDAO.existByPk(id)){
            throw new NotFoundException("This supplier is not founded");
        }
        return supplierDAO.findByPk(id);
    }

    @Override
    public SupplierDTO searchSupplier(String text) {
        return converter.fromSupplier(supplierDAO.searchsupplier(text));
    }

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) throws DuplicateException {
        if (supplierDAO.existByPk(supplierDTO.getSid())) {
            throw new DuplicateException("This Supplier id is already added!");
        } else {
            supplierDAO.save(converter.toSupplier(supplierDTO));
            return supplierDTO;
        }
    }

    @Override
    public SupplierDTO updatSupplier(SupplierDTO supplierDTO) throws NotFoundException {
        if (!supplierDAO.existByPk(supplierDTO.getSid())) {
            throw new NotFoundException("Customer not found!");
        } else {
            supplierDAO.update(converter.toSupplier(supplierDTO));
            return supplierDTO;
        }
    }

    @Override
    public List<SupplierDTO> searchSupplierList(String text) throws NotFoundException {
        List<Supplier> all = supplierDAO.searchByText(text);
        List<SupplierDTO> supplierDTOList= new ArrayList<>();
        for (int i =0 ; i< all.size(); i++){
            Supplier supplier = all.get(i);
            supplierDTOList.add(i,converter.fromSupplier(supplier));
        }
        return supplierDTOList;
    }

    @Override
    public boolean deleteSupplier(String id) throws NotFoundException {
        if (!supplierDAO.existByPk(id)){
            throw new NotFoundException("This supplier id is not found");
        }
        return supplierDAO.deleteByPk(id);
    }

    @Override
    public String generateNewSupplierId() throws SQLException {
        String newSupplierId = supplierDAO.findNewSupplierId();
        return newSupplierId;
    }

    @Override
    public boolean searchDuplicate(String id) throws NotFoundException {
        if (!supplierDAO.existByPk(id)){
            return false;
        }
        return true;
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        List<Supplier> all = supplierDAO.findAll();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (int i =0 ; i<all.size() ; i++){
            Supplier supplier = all.get(i);
            SupplierDTO supplierDTO = converter.fromSupplier(supplier);
            supplierDTOList.add(supplierDTO);
        }
        return supplierDTOList;
    }

    @Override
    public Optional<SupplierDTO> searchSupplier1(String id) {
        return Optional.empty();
    }

    @Override
    public List<String> getSuppliers() throws SQLException {
        List<String> companies = supplierDAO.findCompanies();
        return companies;
    }
}
