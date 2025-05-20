package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BorrowedBook;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedBookRepository;
import com.example.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    /**
     * Marks a book as borrowed by a borrower.
     * The book must not already be borrowed.
     *
     * @param borrowerId the ID of the borrower
     * @param bookId     the ID of the book
     * @return the BorrowedBook record created
     */
    public BorrowedBook borrowBook(Long borrowerId, Long bookId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("Borrower not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isBorrowed()) {
            throw new RuntimeException("Book is already borrowed");
        }

        book.setBorrowed(true);
        bookRepository.save(book);

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBorrower(borrower);
        borrowedBook.setBook(book);
        borrowedBook.setBorrowedAt(LocalDateTime.now());

        return borrowedBookRepository.save(borrowedBook);
    }

    /**
     * Marks a book as returned by a borrower.
     * Updates the book status and records return timestamp.
     *
     * @param borrowerId the ID of the borrower
     * @param bookId     the ID of the book
     * @return the updated BorrowedBook record
     */
    public BorrowedBook returnBook(Long borrowerId, Long bookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findByBookIdAndBorrowerId(bookId, borrowerId)
                .orElseThrow(() -> new RuntimeException("No record found for borrower and book"));

        Book book = borrowedBook.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);

        borrowedBook.setReturnedAt(LocalDateTime.now());
        return borrowedBookRepository.save(borrowedBook);
    }
}