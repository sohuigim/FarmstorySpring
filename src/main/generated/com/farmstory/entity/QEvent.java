package com.farmstory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -196074805L;

    public static final QEvent event = new QEvent("event");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> End_date = createDateTime("End_date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> Start_date = createDateTime("Start_date", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

