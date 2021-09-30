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
	
	@Autowired
	private UserRepository  repository;
	
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

	public List<User> findAll() {
		return this.repository.findAll();
	}
}
