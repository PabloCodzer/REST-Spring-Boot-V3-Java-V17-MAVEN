package TREST.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import TREST.Models.User;
import TREST.Repositories.UserRepository;

@Service
public class UserService 
{
	
	UserRepository userRepo;
	
	//@Autowired
	public UserService( UserRepository  userRepo)
	{
		this.userRepo =  userRepo;
	}
	
	public List<User> findAll()
	{
		var UserList = userRepo.findAll();
		return UserList;
	}

}
