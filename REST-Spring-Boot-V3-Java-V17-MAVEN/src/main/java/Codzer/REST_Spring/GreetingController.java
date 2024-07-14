package Codzer.REST_Spring;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Aham, %s";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public ResponseEntity<Greeting> greeting_EP(@RequestParam(value = "name", defaultValue = "..sei") String name  )
	{
		return ResponseEntity.ok(new Greeting(counter.incrementAndGet(), name));
	}
}
