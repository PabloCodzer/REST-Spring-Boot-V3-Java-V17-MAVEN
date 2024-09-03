package TREST.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TREST.Models.Book;
import TREST.Services.BookService;

@RestController
@RequestMapping("/books")
public class BookController 
{
	@Autowired
	BookService bookService; 
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>> aham()
	{
		var BookList = bookService.findAll();
		return ResponseEntity.ok().body(BookList);
	}
}
