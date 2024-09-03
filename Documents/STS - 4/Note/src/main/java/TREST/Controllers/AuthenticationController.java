package TREST.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TREST.DTO.LoginUserDto;
import TREST.DTO.RegisterUserDto;
import TREST.Models.User;
import TREST.Services.AuthenticationService;
import TREST.Services.JwtServices;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtServices jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtServices jwtService, AuthenticationService authenticationService) 
    {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) 
    {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) 
    {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);    
        
    	var crypto = new BCryptPasswordEncoder();
    	var password = loginUserDto.getPassword();
    	
    	boolean password_probe = crypto.matches(password, authenticatedUser.getPassword());
    	
        if ( password_probe ) 
        {        
        	String jwtToken = jwtService.generateToken(authenticatedUser);
        	LoginResponse loginResponse = new LoginResponse();
        	loginResponse.setExpiresIn(jwtService.getExpirationTime());
        	loginResponse.setToken(jwtToken);
        	return ResponseEntity.ok().body(loginResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}