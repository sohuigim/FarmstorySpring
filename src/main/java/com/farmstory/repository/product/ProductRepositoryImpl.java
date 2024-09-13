package com.farmstory.repository.product;

import com.farmstory.entity.Product;
import com.farmstory.entity.QProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private QProduct qProduct = QProduct.product;


    @Override
    public List<Product> selectProducts() {
        return queryFactory
                .select(qProduct)
                .from(qProduct)
                .fetch();
    }

    @Override
    public Product selectProduct(int prodNo) {
        return null;
    }

    public Page<Product> findAllWithPaging(Pageable pageable) {

        JPAQuery<Product> query = queryFactory
                .selectFrom(qProduct)
                .orderBy(qProduct.prodNo.asc()); // 정렬 조건 예시

        // 페이징 적용
        List<Product> products = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 개수 조회
        long total = queryFactory
                .selectFrom(qProduct)
                .fetchCount();

        return new PageImpl<>(products, pageable, total);

    }

    private BooleanExpression isAvailable() {
        // 예시 조건: product.isAvailable이 true일 때만 조회
        return qProduct.isNotNull();
    }

}
