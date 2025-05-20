package com.example.library.controller;

import com.example.library.model.BorrowedBook;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowers/{borrowerId}")
public class BorrowedBookController {
    @Autowired
    private LibraryService libraryService;


    /**
     * Allows a borrower to borrow a book by book ID.
     * Book must not already be borrowed.
     *
     * @param borrowerId the ID of the borrower
     * @param bookId     the ID of the book
     * @return the borrowing record
     */
    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<BorrowedBook> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        BorrowedBook borrowedBook = libraryService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok(borrowedBook);
    }

    /**
     * Allows a borrower to return a previously borrowed book.
     *
     * @param borrowerId the ID of the borrower
     * @param bookId     the ID of the book
     * @return the updated borrowing record with return timestamp
     */
    @PostMapping("/return/{bookId}")
    public ResponseEntity<BorrowedBook> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        BorrowedBook returnedBook = libraryService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok(returnedBook);
    }
}
