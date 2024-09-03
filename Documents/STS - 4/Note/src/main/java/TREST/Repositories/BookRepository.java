package TREST.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TREST.Models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> 
{}