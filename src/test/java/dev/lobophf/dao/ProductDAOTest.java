package dev.lobophf.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lobophf.dao.utils.SideConnection;
import dev.lobophf.domain.Product;

public class ProductDAOTest {

  private Product product;
  private ProductDAO productDAO;

  ProductDAOTest() {
    product = createProduct();
    productDAO = new ProductDAO();
  }

  @BeforeEach
  void init() throws SQLException {
    SideConnection.deleteAllProductsFromDatabase();
  }

  @Test
  public void should_Return_One_When_Add_Product_To_Database() throws Exception {
    int result = productDAO.register(product);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void should_Return_One_When_Remove_Product_From_Database() throws Exception {
    SideConnection.addProductToDatabase(product);
    int result = productDAO.remove(product);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void should_Return_ProductOptional_From_Database() throws Exception {
    Optional<Product> productOptional = productDAO.search("C1");
    Assertions.assertNotNull(productOptional);
  }

  @Test
  public void should_Return_NotNullProduct_From_Database() throws Exception {
    SideConnection.addProductToDatabase(product);
    Product product = productDAO.search("C1").get();
    Assertions.assertNotNull(product);
  }

  @Test
  public void should_Return_One_When_Product_changes() throws Exception {
    SideConnection.addProductToDatabase(product);
    Product product1 = new Product();
    product1.setCode(product.getCode());
    product1.setPrice(BigDecimal.valueOf(99.99));
    product1.setDescription("changed product");

    int result = productDAO.update(product1);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void should_Return_Set_Of_Products() throws Exception {
    ArrayList<Product> arrayListOfProducts = createSetOfProducts();
    arrayListOfProducts.forEach(p -> {
      try {
        SideConnection.addProductToDatabase(p);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    for (int i = 0; i < arrayListOfProducts.size(); i++) {
      Product productFromDatabase = productDAO.search("C" + i).get();
      Assertions.assertTrue(productFromDatabase.getCode().equals(arrayListOfProducts.get(i).getCode()));
      BigDecimal b = productFromDatabase.getPrice();
      BigDecimal a = arrayListOfProducts.get(i).getPrice();
      BigDecimal diff = a.add(b.negate()).divide(a).abs();
      int compare = diff.compareTo(BigDecimal.valueOf(0.01));
      Assertions.assertTrue(compare == -1 || compare == 0);
      Assertions.assertTrue(productFromDatabase.getDescription().equals(arrayListOfProducts.get(i).getDescription()));
    }
  }

  public Product createProduct() {
    Product product = new Product();
    product.setCode("C1");
    product.setPrice(BigDecimal.valueOf(4.99));
    product.setDescription("The product");
    return product;
  }

  public ArrayList<Product> createSetOfProducts() {
    ArrayList<Product> arrayOfProducts = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Product product = new Product();
      product.setCode("C" + i);
      product.setPrice(BigDecimal.valueOf(i + 1.0));
      product.setDescription("Description " + i);
      arrayOfProducts.add(product);
    }
    return arrayOfProducts;
  }
}
