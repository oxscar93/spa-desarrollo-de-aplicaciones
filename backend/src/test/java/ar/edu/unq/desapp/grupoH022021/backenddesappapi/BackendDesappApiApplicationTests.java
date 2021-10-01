package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;

@SpringBootTest
class BackendDesappApiApplicationTests {
	
	@Autowired
    private UserService userService;

	@Test
	void contextLoads() {
		UserDto user = new UserDto();

		user.name = "guille";
		user.lastName = "salvatore";

		UserDto user2 = new UserDto();

		user2.name = "osc";
		user2.lastName = "lescano";

		userService.save(user);
		userService.save(user2);
		
//		assertTrue(user.name.equalsIgnoreCase("asd"));
//		assertTrue(user2.name.equalsIgnoreCase("guille"));
	}

}
