package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column
	public String name;

	@Column
	public String lastname;

	@Column
	public String address;

	@Column
	public String password;

	@Column
	public String criptoWallet;

	@Column
	public String cvu;

	@Column
	public String email;

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
