package com.example.library.controller;

import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    /**
     * Registers a new borrower.
     *
     * @param borrower the borrower to register
     * @return the registered borrower
     */
    @PostMapping
    public Borrower register(@Valid @RequestBody Borrower borrower) {
        return borrowerService.registerBorrower(borrower);
    }
}