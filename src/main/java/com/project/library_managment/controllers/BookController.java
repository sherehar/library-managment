package com.project.library_managment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.library_managment.Services.BookService;
import com.project.library_managment.models.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    
    // Fetch all the books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    
    // Fetch a specific book (by id)
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id).orElseThrow();
        return ResponseEntity.ok(book);
    }
    
    
    // Update a specific book (by id)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book updatedBook) {
        Book result = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(result);
    }
    
    
    // Delete a book 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    
    // Borrow a book 
    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        bookService.borrowBook(bookId, userId);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    
    // Return a book 
    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully.");
    }
}
