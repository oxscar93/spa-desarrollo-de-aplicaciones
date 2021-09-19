package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;

@RestController
@EnableAutoConfiguration
public class UserController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/api/users")
    public List<User> allCars() {
        List<User> list = userService.findAll();;
        return list;
    }
	
	@RequestMapping(value = "/api/version", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getVersion() {
    	String version = "0.2.1";
		Map<String, String> resultado = new HashMap<String, String>();
		resultado.put("version", version);
		return ResponseEntity.ok().body(resultado);
	}
}
