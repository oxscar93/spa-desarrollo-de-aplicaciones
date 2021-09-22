package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "criptos")
public class Cripto {

	@Id
	public Integer id;
	
	@Column
	public String name;

	@Column
	public Double price;
	
	public Cripto() {
		super();
	}

	public Cripto(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
}
