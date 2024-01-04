package lk.ijse.shaili.system.Service.custom.impl;



import lk.ijse.shaili.system.Dao.DaoFactory;
import lk.ijse.shaili.system.Dao.DaoTypes;
import lk.ijse.shaili.system.Dao.custom.CustomerDAO;
import lk.ijse.shaili.system.Db.DBConnection;
import lk.ijse.shaili.system.Dto.BestCustomerDTO;
import lk.ijse.shaili.system.Dto.CustomerDTO;
import lk.ijse.shaili.system.Entity.Customer;
import lk.ijse.shaili.system.Service.custom.CustomerService;
import lk.ijse.shaili.system.Service.exception.*;
import lk.ijse.shaili.system.Service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final Converter converter;
    private final Connection connection;
    private final CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        connection = DBConnection.getDbConnection().getConnection();
        converter = new Converter();
        customerDAO = DaoFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER);

    }
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        if (customerDAO.existByPk(customerDTO.getCid())) {
            throw new DuplicateException("This Customer id is already added!");
        } else {
            customerDAO.save(converter.toCustomer(customerDTO));
            return customerDTO;
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if (!customerDAO.existByPk(customerDTO.getCid())) {
            throw new NotFoundException("Customer not found!");
        } else {
            Customer customer = converter.toCustomer(customerDTO);
            System.out.println("Service "+customer);
            customerDAO.update(converter.toCustomer(customerDTO));
            return customerDTO;
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id, String type) throws NotFoundException {
        return converter.fromCustomer(customerDAO.findCustomer(id,type));
    }

    @Override
    public boolean deleteCustomer(String id) throws NotFoundException {
        if (!customerDAO.existByPk(id)){
            throw new NotFoundException("This customer id is not found");
        }
        return customerDAO.deleteByPk(id);
    }

    @Override
    public String generateNewCustomerId() throws SQLException {
        String newCustomerId = customerDAO.findNewCustomerId();
        return newCustomerId;
    }

    @Override
    public boolean searchDuplicate(String id) throws NotFoundException {
        if (!customerDAO.existByPk(id)){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Customer> findCustomer(String id) {
        if (!customerDAO.existByPk(id)){
            return Optional.empty();
        }
         return customerDAO.findByPk(id);
    }

    @Override
    public long countCustomer() {
        return customerDAO.count();
    }

    @Override
    public BestCustomerDTO findBestCustomer() {
        return converter.fromBestCustomer(customerDAO.findBestCustomer());
    }
}
