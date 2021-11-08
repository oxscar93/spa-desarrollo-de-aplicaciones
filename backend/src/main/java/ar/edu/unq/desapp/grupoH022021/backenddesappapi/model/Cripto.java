package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "criptos")
public class Cripto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column
	public String name;
	
	public Cripto() {
		super();
	}

	public Cripto(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
