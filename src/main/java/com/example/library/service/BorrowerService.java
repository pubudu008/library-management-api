package com.example.library.service;

import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    /**
     * Registers a new borrower in the system.
     * If a borrower with the same email already exists, an exception is thrown.
     *
     * @param borrower the borrower to register
     * @return the saved borrower entity
     */
    public Borrower registerBorrower(Borrower borrower) {
        if (borrowerRepository.findByEmail(borrower.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Borrower with this email already exists.");
        }
        return borrowerRepository.save(borrower);
    }
}