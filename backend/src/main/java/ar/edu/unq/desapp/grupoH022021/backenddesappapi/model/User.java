package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	public Integer id;

	@Column
	public String name;

	@Column
	public String lastname;

	public User() {
		super();
	}

	public User(Integer id, String name, String lastname) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}
}
