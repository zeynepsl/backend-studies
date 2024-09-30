package com.zeynep.study_cases.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeynep.study_cases.mapper.DtoMapper;
import com.zeynep.study_cases.model.AuthorEntity;
import com.zeynep.study_cases.model.BookEntity;
import com.zeynep.study_cases.model.dto.AuthorDto;
import com.zeynep.study_cases.model.dto.BookDto;
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
    private final DtoMapper mapper;
    QAuthorEntity qAuthor;
    QBookEntity qBook;
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public AuthorBookService(AuthorRepository authorRepository, DtoMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
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

        queryFactory = new JPAQueryFactory(entityManager);
        qAuthor = QAuthorEntity.authorEntity;
        qBook = QBookEntity.bookEntity;
    }

    public List<AuthorDto> getAuthorsWithBooksOptimized() {
        List<AuthorEntity> authors = queryFactory
                .selectFrom(qAuthor)
                .leftJoin(qAuthor.books, qBook).fetchJoin()
                .fetch();
        return authors.stream()
                .map(mapper::mapEntityToAuthorDto)
                .toList();
    }

    public List<AuthorDto> getAuthorsWithBooks() {
        List<AuthorDto> authorDtos = allAuthorQuery();
        for (AuthorDto authorDto : authorDtos) {
            List<BookDto> booksOfAuthor = booksByAuthorQuery(authorDto.getId());
            authorDto.setBookDtos(booksOfAuthor);
        }
        return authorDtos;
    }

    public List<AuthorDto> allAuthorQuery() {
        List<Tuple> tuples = queryFactory.select(qAuthor.id, qAuthor.name)
                .from(qAuthor)
                .fetch();
        return tuples.stream()
                .map(mapper::mapTupleToAuthorDto)
                .toList();
    }

    public List<BookDto> booksByAuthorQuery(Long authorId) {
        List<Tuple> tuples = queryFactory.select(qBook.id, qBook.title, qBook.author.id)
                .from(qBook)
                .join(qBook.author, qAuthor)
                .where(qBook.author.id.eq(authorId))
                .fetch();
        return tuples.stream()
                .map(mapper::mapTupleToBookDto)
                .toList();
    }

    public List<AuthorDto> authorIdQuery(Long authorId) {
        List<Tuple> tuples = queryFactory.select(qAuthor.id, qAuthor.name)
                .from(qAuthor)
                .where(qAuthor.id.eq(authorId))
                .fetch();
        return tuples.stream()
                .map(mapper::mapTupleToAuthorDto)
                .toList();
    }

    public AuthorDto getAuthorById(Long authorId) {
        List<AuthorDto> authors = authorIdQuery(authorId);

        if (!authors.isEmpty()) {
            AuthorDto authorDto = authors.get(0);
            List<BookDto> booksOfAuthor = booksByAuthorQuery(authorId);
            authorDto.setBookDtos(booksOfAuthor);
            return authorDto;
        } else {
            return null;
        }
    }

    public boolean isAuthorHasBooks(Long authorId) {
        return queryFactory.selectOne()
                .from(qBook)
                .where(qBook.author.id.eq(authorId))
                .fetchFirst() != null;
    }

    public List<Tuple> wildcardSearchOnBooks(String namePattern) {
        return queryFactory.select(qBook.id, qBook.title)
                .from(qBook)
                .join(qBook.author, qAuthor)
                .where(qBook.title.likeIgnoreCase(namePattern))
                .fetch();
    }

}
