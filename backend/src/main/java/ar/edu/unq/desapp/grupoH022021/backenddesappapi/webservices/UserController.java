package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects.Log;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class UserController {

	@Autowired
    private UserService userService;

	@Log
	@GetMapping("/api/users")
    public List<User> users() {
        List<User> list = userService.findAll();
        return list;
    }

	@Log
	@PostMapping("/api/users")
	public void register(@Valid @RequestBody UserDto user) {
		userService.save(user);
	}
}
