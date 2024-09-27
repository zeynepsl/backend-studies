package com.zeynep.study_cases.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeynep.study_cases.model.AuthorEntity;
import com.zeynep.study_cases.model.BookEntity;
import com.zeynep.study_cases.model.query.QAuthorEntity;
import com.zeynep.study_cases.model.query.QBookEntity;
import com.zeynep.study_cases.repository.AuthorRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorBookService {

    private final AuthorRepository authorRepository;

    public AuthorBookService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init(){
        AuthorEntity author1 = new AuthorEntity("Author 1");
        AuthorEntity author2 = new AuthorEntity("Author 2");
        AuthorEntity author3 = new AuthorEntity("Author 3");

        BookEntity book1 = new BookEntity("Book 1", author1);
        BookEntity book2 = new BookEntity("Book 2", author1);
        BookEntity book3 = new BookEntity("Book 3", author2);
        BookEntity book4 = new BookEntity("Book 4", author3);

        author1.setBooks(List.of(book1, book2));
        author2.setBooks(List.of(book3));
        author3.setBooks(List.of(book4));

        authorRepository.saveAll(Arrays.asList(author1, author2, author3));
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Tuple> findAuthors(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAuthorEntity author = QAuthorEntity.authorEntity;
        QBookEntity book = QBookEntity.bookEntity;
        List<Tuple> tuple = queryFactory.select(author.id, author.name, book.title)
                .from(author)
                .leftJoin(author.books, book)
                .groupBy(author.id, book.title)
                .fetch();

        return tuple;
    }


}
