package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.BorrowedBook;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedBookRepository;
import com.example.library.repository.BorrowerRepository;

import com.example.library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

        @InjectMocks
        private LibraryService libraryService;

        @Mock private BookRepository bookRepository;
        @Mock private BorrowerRepository borrowerRepository;
        @Mock private BorrowedBookRepository borrowedBookRepository;

        private Borrower borrower;
        private Book book;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            borrower = new Borrower(1L, "Alice", "alice@example.com");
            book = new Book(1L, "123", "Clean Code", "Uncle Bob", false);
        }

        @Test
        void borrowBook_shouldSucceed_whenBookIsAvailable() {
            when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
            when(bookRepository.save(any(Book.class))).thenReturn(book);
            when(borrowedBookRepository.save(any(BorrowedBook.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            BorrowedBook borrowed = libraryService.borrowBook(1L, 1L);

            assertNotNull(borrowed.getBorrowedAt());
            assertEquals(borrower, borrowed.getBorrower());
            assertEquals(book, borrowed.getBook());
            assertTrue(book.isBorrowed());
        }

        @Test
        void borrowBook_shouldFail_whenBookAlreadyBorrowed() {
            book.setBorrowed(true);
            when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

            Exception ex = assertThrows(RuntimeException.class, () -> {
                libraryService.borrowBook(1L, 1L);
            });

            assertEquals("Book is already borrowed", ex.getMessage());
        }
    }
