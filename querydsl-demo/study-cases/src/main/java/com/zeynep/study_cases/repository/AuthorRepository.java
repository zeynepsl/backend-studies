package com.zeynep.study_cases.repository;

import com.zeynep.study_cases.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
