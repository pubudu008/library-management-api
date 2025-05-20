package com.example.library;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerBook_shouldSaveBook_whenIsbnIsUniqueOrConsistent() {
        Book book = new Book(null, "123-456", "Effective Java", "Joshua Bloch", false);
        when(bookRepository.findByIsbn("123-456")).thenReturn(Collections.emptyList());
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.registerBook(book);

        assertEquals("123-456", savedBook.getIsbn());
        verify(bookRepository).save(book);
    }

    @Test
    void registerBook_shouldThrowException_whenIsbnConflictDetected() {
        Book newBook = new Book(null, "123-456", "Java Puzzlers", "Joshua Bloch", false);
        Book existingBook = new Book(1L, "123-456", "Effective Java", "Joshua Bloch", false);
        when(bookRepository.findByIsbn("123-456")).thenReturn(List.of(existingBook));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.registerBook(newBook);
        });

        assertEquals("ISBN conflict: title/author mismatch", exception.getMessage());
    }
}

