package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository  repository;

	@Autowired
	public UserService(UserRepository  repository){
		this.repository = repository;
	}
	@Transactional
	public User save(UserDto user) {

		User entity = new User();

		entity.name = user.name;
		entity.lastname = user.lastName;
		entity.address = user.address;
		entity.email = user.email;
		entity.password = user.password;
		entity.criptoWallet = user.criptoWallet;
		entity.cvu = user.cvu;

		return this.repository.save(entity);
	}

	public User findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public User login(String username, String password) throws Exception {
		User user = this.repository.findByName(username);

		if (user == null || !user.password.equals(password)){
			throw new Exception("User does not exist or password is invalid");
		}

		return user;
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}
}
