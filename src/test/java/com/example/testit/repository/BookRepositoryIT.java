package com.example.testit.repository;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.testit.model.Book;

import com.example.testit.model.Member;
import com.example.testit.model.Status;


@SpringBootTest
class BookRepositoryIT {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test1StatusAndMember(){


        Member m1 = new Member("m1");
        Member m2 = new Member("m2");

        m1.setId(1L);
        m2.setId(2L);

        memberRepository.save(m1);
        memberRepository.save(m2);

        Book b1 = new Book("La","Haut");
        Book b2 = new Book("En","Bas");

        b1.setId(1L);
        b2.setId(2L);

        b1.setBorrower(m1);
        b1.setStatus(Status.EMPRUNTE);

        b2.setStatus(Status.DISPONIBLE);

        bookRepository.save(b1);
        bookRepository.save(b2);

        List<Book> result = bookRepository.findByMemberAndStatus(m1.getId(),Status.EMPRUNTE);

        assertTrue(result.size()==1);
        //dans la requete y a un OR au lieu d'un AND



    }
    
}
