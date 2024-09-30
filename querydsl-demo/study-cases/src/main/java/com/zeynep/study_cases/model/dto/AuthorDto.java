package com.zeynep.study_cases.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDto {
    Long id;
    String name;
    List<BookDto> bookDtos;
}
