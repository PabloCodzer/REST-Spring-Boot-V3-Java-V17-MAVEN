package TREST.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Welcome 
{
	@GetMapping
	public String aham()
	{
		return "Welcome fdp";
	}
}
