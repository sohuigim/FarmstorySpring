package com.farmstory.repository.product;

import com.farmstory.dto.MarketPageRequestDTO;
import com.farmstory.entity.Product;
import com.farmstory.entity.QProduct;
import com.querydsl.core.Tuple;
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

    @Override
    public Page<Tuple> selectProductAllForList(MarketPageRequestDTO marketPageRequestDTO, Pageable pageable, int catetype) {

        List<Tuple> content = null;
        long total = 0;

        if(catetype == 0) {
            content = queryFactory.select(qProduct, qProduct)
                    .from(qProduct)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qProduct.prodNo.desc())
                    .fetch();
            total = queryFactory
                    .select(qProduct.count())
                    .from(qProduct)
                    .fetchOne();
        }else if(catetype == 1) {
            content = queryFactory.select(qProduct, qProduct)
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(1))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qProduct.prodNo.desc())
                    .fetch();
            total = queryFactory
                    .select(qProduct.count())
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(1))
                    .fetchOne();
        }else if(catetype == 2) {
            content = queryFactory.select(qProduct, qProduct)
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(2))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qProduct.prodNo.desc())
                    .fetch();
            total = queryFactory
                    .select(qProduct.count())
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(2))
                    .fetchOne();
        }else if(catetype == 3) {
            content = queryFactory.select(qProduct, qProduct)
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(3))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qProduct.prodNo.desc())
                    .fetch();
            total = queryFactory
                    .select(qProduct.count())
                    .from(qProduct)
                    .where(qProduct.prodCateType.eq(3))
                    .fetchOne();
        }

        // 페이징 처리를 위해 page 객체 리턴
        return new PageImpl<Tuple>(content, pageable, total);
    }

}
