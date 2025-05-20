package com.example.library.repository;

import com.example.library.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    Optional<BorrowedBook> findByBookIdAndBorrowerId(Long bookId, Long borrowerId);
}
