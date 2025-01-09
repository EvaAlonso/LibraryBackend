package com.BootcampFactoria.Library.repository;

import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository <Loan, Integer>{
    List<Loan> findByBookAndReturnDateIsNull(Book book);
}
