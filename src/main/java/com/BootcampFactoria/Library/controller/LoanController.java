package com.BootcampFactoria.Library.controller;

import com.BootcampFactoria.Library.model.Loan;
import com.BootcampFactoria.Library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam int bookId, @RequestParam int memberId) {

        try {
            Loan loan = loanService.createLoan(bookId, memberId);
            return new ResponseEntity<>(loan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Loan> returnLoan(@RequestParam int loanId) {
        try {
            Loan loan = loanService.returnLoan(loanId);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Loan>> getLoansByMember(@PathVariable int memberId) {
        List<Loan> loans = loanService.getLoansByMember(memberId);
        if (loans.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> getLoansByBook(@PathVariable int bookId) {
        List<Loan> loans = loanService.getLoansByBook(bookId);
        if (loans.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
}

