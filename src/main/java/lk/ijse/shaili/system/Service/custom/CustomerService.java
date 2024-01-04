package lk.ijse.shaili.system.Service.custom;

import lk.ijse.shaili.system.Dto.BestCustomerDTO;
import lk.ijse.shaili.system.Dto.CustomerDTO;
import lk.ijse.shaili.system.Entity.Customer;
import lk.ijse.shaili.system.Service.SuperService;
import lk.ijse.shaili.system.Service.exception.*;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerService extends SuperService {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException;

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws  NotFoundException;

    public CustomerDTO searchCustomer(String id,String type) throws NotFoundException;

    public boolean deleteCustomer(String id) throws  NotFoundException;

    public String generateNewCustomerId() throws SQLException;

    public boolean searchDuplicate(String id) throws NotFoundException;

    public Optional<Customer> findCustomer(String id);

    long countCustomer();

    BestCustomerDTO findBestCustomer();
}
