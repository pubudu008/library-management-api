package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Registers a new book in the library.
     *
     * @param book the book to register
     * @return the registered book
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book registerBook(@Valid @RequestBody Book book) {
        return bookService.registerBook(book);
    }

    /**
     * Retrieves all books in the library.
     *
     * @return list of books
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}

