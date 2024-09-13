package com.farmstory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -1417390512L;

    public static final QComment comment = new QComment("comment");

    public final NumberPath<Integer> artNo = createNumber("artNo", Integer.class);

    public final NumberPath<Integer> commentNo = createNumber("commentNo", Integer.class);

    public final StringPath commentRegDate = createString("commentRegDate");

    public final StringPath commentRegIp = createString("commentRegIp");

    public final StringPath userUid = createString("userUid");

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

