package com.zeynep.study_cases;

import com.zeynep.study_cases.model.AuthorEntity;
import com.zeynep.study_cases.model.BookEntity;
import com.zeynep.study_cases.model.dto.AuthorDto;
import com.zeynep.study_cases.repository.AuthorRepository;
import com.zeynep.study_cases.service.AuthorBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class StudyCasesApplicationTests {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorBookService authorBookService;

    @BeforeEach
    void setup() {
        AuthorEntity author1 = new AuthorEntity("Jane doe");
        AuthorEntity author2 = new AuthorEntity("Linda Roe");
        AuthorEntity author3 = new AuthorEntity("Johnny Doe");

        BookEntity book1 = new BookEntity("Book 1", author1);
        BookEntity book2 = new BookEntity("Book 2", author1);
        BookEntity book3 = new BookEntity("Book 3", author2);
        BookEntity book4 = new BookEntity("Book 4", author3);

        author1.setBooks(List.of(book1, book2));
        author2.setBooks(List.of(book3));
        author3.setBooks(List.of(book4));

        authorRepository.saveAll(Arrays.asList(author1, author2, author3));
    }

    @Test
    public void should_get_authors_with_booksOfThem() {
        List<AuthorDto> authors = authorBookService.getAuthorsWithBooksOptimized();

        assertNotNull(authors);
        assertEquals(3, authors.size());

        assertNotNull(authors.get(0).getBookDtos());
        assertNotNull(authors.get(1).getBookDtos());
        assertNotNull(authors.get(2).getBookDtos());

        assertEquals("Jane doe", authors.get(0).getName());
        assertEquals("Linda Roe", authors.get(1).getName());
        assertEquals("Johnny Doe", authors.get(2).getName());
    }

}
