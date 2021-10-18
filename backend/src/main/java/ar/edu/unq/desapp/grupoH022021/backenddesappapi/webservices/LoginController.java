package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TokenDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.TokenService;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/auth")
    public ResponseEntity<TokenDto> login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws Exception {
        try{
            userService.login(username, pwd);
            return new ResponseEntity(this.tokenService.getJWT(username), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
}