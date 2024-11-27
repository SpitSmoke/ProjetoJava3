package dev.lobophf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.lobophf.dao.jdbc.ConnectionFactory;
import dev.lobophf.domain.Customer;

public class CustomerDAO implements ICustomerDAO {

  @Override
  public Integer register(Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "INSERT INTO tb_customer(id, name, cpf) VALUES(nextval('sq_customer'), ?, ?)";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, customer.getName());
      preparedStatement.setString(2, customer.getCpf());
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
  public Integer remove(Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "DELETE FROM tb_customer WHERE cpf = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, customer.getCpf());
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
  public Optional<Customer> search(String cpf) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "SELECT * FROM tb_customer WHERE cpf = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, cpf);
      Customer customer = new Customer();
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        customer.setName(resultSet.getString("name"));
        customer.setCpf(resultSet.getString("cpf"));
      }
      Optional<Customer> customerOptional = Optional.of(customer);
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
  public List<Customer> searchAll() throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "SELECT * FROM tb_customer";
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();
      List<Customer> customersList = new ArrayList<Customer>();

      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setName(resultSet.getString("name"));
        customer.setCpf(resultSet.getString("cpf"));
        customersList.add(customer);
      }
      return customersList;
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
  public Integer update(Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "UPDATE tb_customer SET name = ? WHERE cpf = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, customer.getName());
      preparedStatement.setString(2, customer.getCpf());
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
