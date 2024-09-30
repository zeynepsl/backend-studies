package com.zeynep.study_cases.endpoint;

import com.zeynep.study_cases.model.dto.AuthorDto;
import com.zeynep.study_cases.service.AuthorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final AuthorBookService authorBookService;

    @GetMapping("get-authors")
    public List<AuthorDto> getAuthorsWithBooks() {
        return authorBookService.getAuthorsWithBooks();
    }

    @GetMapping("get-authors-optimized")
    public List<AuthorDto> getAuthorsWithBooksOptimized() {
        return authorBookService.getAuthorsWithBooksOptimized();
    }

}
