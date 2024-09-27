package com.zeynep.study_cases.model.query;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.zeynep.study_cases.model.AuthorEntity;
import com.zeynep.study_cases.model.BookEntity;

import javax.annotation.processing.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAuthorEntity is a Querydsl query type for AuthorEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthorEntity extends EntityPathBase<AuthorEntity> {

    private static final long serialVersionUID = 390782410L;

    public static final QAuthorEntity authorEntity = new QAuthorEntity("authorEntity");

    public final ListPath<BookEntity, QBookEntity> books = this.<BookEntity, QBookEntity>createList("books", BookEntity.class, QBookEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QAuthorEntity(String variable) {
        super(AuthorEntity.class, forVariable(variable));
    }

    public QAuthorEntity(Path<? extends AuthorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorEntity(PathMetadata metadata) {
        super(AuthorEntity.class, metadata);
    }

}

