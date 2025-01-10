package com.BootcampFactoria.Library.service;


import com.BootcampFactoria.Library.exception.BookNotAvailableException;
import com.BootcampFactoria.Library.exception.LoanNotActiveException;
import com.BootcampFactoria.Library.exception.ObjectNotFoundException;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Loan;
import com.BootcampFactoria.Library.model.Member;
import com.BootcampFactoria.Library.repository.BookRepository;
import com.BootcampFactoria.Library.repository.LoanRepository;
import com.BootcampFactoria.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public Loan createLoan(int bookId, int memberId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (bookOptional.isPresent() && memberOptional.isPresent()) {
            Book book = bookOptional.get();
            Member member = memberOptional.get();

            if (!book.getIsAvailable()) {
                throw new BookNotAvailableException(book.getTitle());
            }
            Loan loan = new Loan();
            loan.setBook(book);
            loan.setMember(member);
            loan.setLoanDate(LocalDateTime.now());
            loan.setReturnDate(LocalDateTime.now().plusMonths(1));

            book.setIsAvailable(false);
            bookRepository.save(book);

            return loanRepository.save(loan);
        }
        throw new ObjectNotFoundException("Book", bookId);
    }
    public Loan returnLoan(int loanId){
        Optional<Loan> loanOptional = loanRepository.findById(loanId);

        if (loanOptional.isPresent()){
            Loan loan = loanOptional.get();

            if (!loan.isLoaned()){
                throw new LoanNotActiveException(loan.getBook().getTitle());
            }

            loan.setReturnDate(LocalDateTime.now());
            loan.setLoaned(false);

            Book book = loan.getBook();
            book.setIsAvailable(true);
            bookRepository.save(book);

            return loanRepository.save(loan);
        }
        throw new ObjectNotFoundException("Loan", loanId);
    }
    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }
    public List<Loan> getLoansByMember(int memberId){
        return loanRepository.findByMemberId(memberId);
    }
    public List<Loan> getLoansByBook(int bookId){
        return loanRepository.findByBookId(bookId);
    }
    public boolean isBookLoaned(int bookId){
        List<Loan> loans = loanRepository.findByBookId(bookId);
        for (Loan loan : loans){
            if (loan.isLoaned() && loan.getReturnDate() == null){
                return true;
            }
        }
        return false;
    }
}
