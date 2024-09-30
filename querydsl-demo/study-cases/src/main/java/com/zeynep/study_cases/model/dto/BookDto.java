package com.zeynep.study_cases.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    Long id;
    String title;
    Long authorId;
}
