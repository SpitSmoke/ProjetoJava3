package dev.lobophf.domain;

public class Customer {
	private String name;
	private String cpf;
  
  public Customer(){

  }

  public Customer(String name, String cpf){
    setName(name);
    setCpf(cpf);
  }

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
