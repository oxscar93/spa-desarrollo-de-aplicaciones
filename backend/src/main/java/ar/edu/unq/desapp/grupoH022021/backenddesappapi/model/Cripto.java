package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "criptos")
public class Cripto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column
	public String name;

	@Column
	public Double price;
	
	public Cripto() {
		super();
	}

	public Cripto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
