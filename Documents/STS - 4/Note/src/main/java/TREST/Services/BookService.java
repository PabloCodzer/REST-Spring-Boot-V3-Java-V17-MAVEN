package TREST.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TREST.Models.Book;
import TREST.Repositories.BookRepository;

@Service
public class BookService 
{
	@Autowired
	BookRepository bookRepo;
	
	public List<Book> findAll()
	{
		List<Book> BookList = bookRepo.findAll();
		return BookList;
	}
	
	public Book findById(Long id)
	{
		var BookById = bookRepo.findById(id).get();
		return BookById;
	}
}
