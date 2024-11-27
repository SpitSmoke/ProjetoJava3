package dev.lobophf.dao;

import java.util.List;
import java.util.Optional;

import dev.lobophf.domain.Customer;

public interface ICustomerDAO {

	public Integer register(Customer customer) throws Exception;

	public Integer remove(Customer customer) throws Exception;
  
	public Optional<Customer> search(String cpf) throws Exception;

	public List<Customer> searchAll() throws Exception;
  
	public Integer update(Customer customer) throws Exception;

}
