package com.farmstory.repository.user;

import com.farmstory.dto.pageDTO.PageRequestDTO;
import com.farmstory.entity.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private QUser qUser = QUser.user;
    @Override
    public Page<Tuple> selectUserAllForList(PageRequestDTO pageRequestDTO, Pageable pageable) {
        List<Tuple> content = null;
        long total = 0;
        content = queryFactory.select(qUser, qUser)
                .from(qUser)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qUser.userUid.desc())
                .fetch();
        total = queryFactory
                .select(qUser.count())
                .from(qUser)
                .fetchOne();
        return new PageImpl<Tuple>(content, pageable, total);
    }
}
