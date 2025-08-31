package com.project.library_managment.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.library_managment.models.Book;
import com.project.library_managment.models.User;
import com.project.library_managment.repositories.BookRepository;
import com.project.library_managment.repositories.UserRepository;

@Service
public class BookService {
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    // Create
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    
    // Read
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    
    // Read by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    
    // Update
    public Book updateBook(Long id, Book updatedBook) {
    	Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());

        return bookRepository.save(book);
    }

    
    // Delete
    public void deleteBook(Long id) {
    	bookRepository.deleteById(id);
    }
    
    
    // Create .... Borrow the book
    public Book borrowBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        if (book.isBorrowed()) {
            throw new RuntimeException("Book is already borrowed");
        }
        book.setBorrowed(true);
        book.setBorrowedBy(user);
        return bookRepository.save(book);
    }

    // Create ... return the book
    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (!book.isBorrowed()) {
            throw new RuntimeException("Book is not currently borrowed");
        }
        book.setBorrowed(false);
        book.setBorrowedBy(null);
        return bookRepository.save(book);
    }

}
