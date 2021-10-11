package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserService userService;

    public UserTests(){

    }

    @Test
    public void getAllUsers() {
        List<User> users = this.userService.findAll();

		assertTrue("Users not found", users.size() == 2);
    }

    @Test
    public void getById() {
        User user = this.userService.findByID(1);

        assertTrue("Users not found", user.name.equals("guille"));
    }
}
