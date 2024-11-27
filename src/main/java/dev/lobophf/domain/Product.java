package dev.lobophf.domain;

import java.math.BigDecimal;

public class Product {
  private Long id;
  private String code;
  private String description;
  private BigDecimal price;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal value) {
    this.price = value;
  }

}
