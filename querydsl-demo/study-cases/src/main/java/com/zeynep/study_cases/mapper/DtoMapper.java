package com.zeynep.study_cases.mapper;

import com.querydsl.core.Tuple;
import com.zeynep.study_cases.model.AuthorEntity;
import com.zeynep.study_cases.model.BookEntity;
import com.zeynep.study_cases.model.dto.AuthorDto;
import com.zeynep.study_cases.model.dto.BookDto;
import com.zeynep.study_cases.model.query.QAuthorEntity;
import com.zeynep.study_cases.model.query.QBookEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoMapper {

    QAuthorEntity qAuthor;
    QBookEntity qBook;

    @PostConstruct
    public void init() {
        qAuthor = QAuthorEntity.authorEntity;
        qBook = QBookEntity.bookEntity;
    }

    public BookDto mapTupleToBookDto(Tuple bTuple) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bTuple.get(qBook.id));
        bookDto.setTitle(bTuple.get(qBook.title));
        bookDto.setAuthorId(bTuple.get(qBook.author.id));
        return bookDto;
    }

    public AuthorDto mapTupleToAuthorDto(Tuple aTuple) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(aTuple.get(qAuthor.id));
        authorDto.setName(aTuple.get(qAuthor.name));
        return authorDto;
    }

    public AuthorDto mapEntityToAuthorDto(AuthorEntity authorEntity) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(authorEntity.getName());
        authorDto.setId(authorEntity.getId());

        List<BookDto> bookDtoList = authorEntity.getBooks()
                .stream()
                .map(this::mapEntityToBookDto)
                .toList();
        authorDto.setBookDtos(bookDtoList);

        return authorDto;
    }

    public BookDto mapEntityToBookDto(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookEntity.getId());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setAuthorId(bookEntity.getAuthor().getId());
        return bookDto;
    }

}
