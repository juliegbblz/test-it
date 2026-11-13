package com.example.testit.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;


class BookServiceTest {

    BookService bookService;

    BookRepository bookRepository;

    MemberRepository memberRepository;
    MailService mailService;

    @BeforeEach
    void setup(){
        bookRepository=Mockito.mock(BookRepository.class);
        memberRepository=Mockito.mock(MemberRepository.class);
        bookService=new BookService(bookRepository, memberRepository, mailService);
    }

    @Test
    public void test1BookBorrowed() {
        Book book = new Book("La","Haut");
        Member member = new Member("Member1");

        book.setId((long)1);
        member.setId((long)1);
        book.setStatus(Status.DISPONIBLE);


        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Mockito.when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        
        Book result = bookService.borrowBook(book.getId(),member.getId());

        assertTrue(result.getStatus()==Status.EMPRUNTE,"Livre bien emprunte");
    }


    
}
