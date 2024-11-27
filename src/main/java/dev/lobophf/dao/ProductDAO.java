package dev.lobophf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.lobophf.dao.jdbc.ConnectionFactory;
import dev.lobophf.domain.Product;

public class ProductDAO implements IProductDAO{

  @Override
  public Integer register(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "INSERT INTO tb_product(id, code, description, price) VALUES(nextval('sq_product'), ?, ?, ?)";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, product.getCode()); 
      preparedStatement.setString(2, product.getDescription());
      preparedStatement.setBigDecimal(3, product.getPrice());

      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        preparedStatement.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
  }

  @Override
  public Integer remove(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "DELETE FROM tb_product WHERE code = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, product.getCode());
      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        preparedStatement.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }

  }

  @Override
  public Optional<Product> search(String code) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "SELECT * FROM tb_product WHERE code = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, code);
      resultSet = preparedStatement.executeQuery();
      Product product = new Product();
      while (resultSet.next()) {
        product.setDescription(resultSet.getString("description"));
        product.setCode(resultSet.getString("code"));
        product.setId(resultSet.getLong("id"));
        product.setPrice(resultSet.getBigDecimal("price"));
      }
      Optional<Product> customerOptional = Optional.of(product);
      return customerOptional;
    } catch (Exception e) {
      throw e;
    } finally {
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        preparedStatement.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
  }

  @Override
    public List<Product> searchAll() throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "SELECT * FROM tb_product";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();
      List<Product> productList = new ArrayList<Product>();

      while (resultSet.next()) {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setCode(resultSet.getString("code"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setDescription(resultSet.getString("description"));
        productList.add(product);
      }
      return productList;
    } catch (Exception e) {
      throw e;
    } finally {
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        preparedStatement.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
  }

  @Override
  public Integer update(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "UPDATE tb_product SET description = ?, price = ? WHERE code = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, product.getDescription());
      preparedStatement.setBigDecimal(2, product.getPrice());
      preparedStatement.setString(3, product.getCode());
      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        preparedStatement.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
  }
}
