package TREST.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TREST.Models.User;
import TREST.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAllUsers()
	{
		var UsersList = userService.findAll();
		return ResponseEntity.ok().body(UsersList);
	}
}
