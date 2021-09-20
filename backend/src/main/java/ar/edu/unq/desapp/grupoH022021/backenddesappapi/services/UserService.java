package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.util.List;

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
	public User save(User model) {
		return this.repository.save(model);
	}

	public User findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<User> findAll() {
		return this.repository.findAll();
	}
}
