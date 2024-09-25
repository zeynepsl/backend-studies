package com.zeynep.study_cases.service;

import com.zeynep.study_cases.model.Author;
import com.zeynep.study_cases.model.Book;
import com.zeynep.study_cases.repository.AuthorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorBookService {
    private final AuthorRepository authorRepository;

    @PostConstruct
    public void init(){
        Author author1 = new Author("Author 1");
        Author author2 = new Author("Author 2");
        Author author3 = new Author("Author 3");

        Book book1 = new Book("Book 1", author1);
        Book book2 = new Book("Book 2", author1);
        Book book3 = new Book("Book 3", author2);
        Book book4 = new Book("Book 4", author3);

        author1.setBooks(List.of(book1, book2));
        author2.setBooks(List.of(book3));
        author3.setBooks(List.of(book4));

        authorRepository.saveAll(Arrays.asList(author1, author2, author3));
    }


}
