package dev.lobophf.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.lobophf.dao.jdbc.ConnectionFactory;
import dev.lobophf.domain.Product;

public class SideConnection {
  public static void deleteAllProductsFromDatabase() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "DELETE FROM tb_product";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      preparedStatement.close();
      connection.close();
    }
  }

  public static void addProductToDatabase(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = ConnectionFactory.getConnection();
      String query = "INSERT INTO tb_product(id, code, description, price) VALUES(nextval('sq_product'), '"
          + product.getCode() + "', '" + product.getDescription() + "', " + product.getPrice() + ")";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      preparedStatement.close();
      connection.close();
    }
  }
}
