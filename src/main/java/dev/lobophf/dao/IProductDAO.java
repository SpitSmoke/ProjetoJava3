package dev.lobophf.dao;

import java.util.List;
import java.util.Optional;

import dev.lobophf.domain.Product;

public interface IProductDAO {

	public Integer register(Product product) throws Exception;

	public Integer remove(Product product) throws Exception;
  
	public Optional<Product> search(String code) throws Exception;

	public List<Product> searchAll() throws Exception;
  
	public Integer update(Product product) throws Exception;

}
