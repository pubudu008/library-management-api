package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Registers a new book copy in the library.
     * If books with the same ISBN exist, the title and author must match.
     *
     * @param book the book to register
     * @return the saved book entity
     */
    public Book registerBook(Book book) {
        // Validate ISBN-title-author consistency
        List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());
        for (Book existing : existingBooks) {
            if (!existing.getTitle().equals(book.getTitle()) ||
                    !existing.getAuthor().equals(book.getAuthor())) {
                throw new IllegalArgumentException("ISBN conflict: title/author mismatch");
            }
        }
        return bookRepository.save(book);
    }

    /**
     * Returns a list of all books in the library.
     *
     * @return list of books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}