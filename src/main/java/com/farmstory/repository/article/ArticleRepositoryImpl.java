package com.farmstory.repository.article;


import com.farmstory.dto.ArticleDTO;
import com.farmstory.dto.ArticlePageRequestDTO;
import com.farmstory.dto.MarketPageRequestDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.QArticle;
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
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QArticle qArticle =  QArticle.article;


    @Override
    public List<Article> selectArticles(String cate) {
        return queryFactory.select(qArticle)
                            .from(qArticle)
                            .where(qArticle.artCate.eq(cate))  
                            .fetch();
    }

    @Override
    public Article selectArticle(int no) {
        return queryFactory.select(qArticle)
                .from(qArticle)
                .where(qArticle.artNo.eq(no))
                .fetchOne();
    }

    @Override
    public Page<Tuple> selectArticleAllForList(ArticlePageRequestDTO articlePageRequestDTO, Pageable pageable, String catetype) {
        List<Tuple> content = null;
        long total = 0;


            content = queryFactory.select(qArticle, qArticle)
                    .from(qArticle)
                    .where(qArticle.artCate.eq(catetype))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qArticle.artNo.desc())
                    .fetch();
            total = queryFactory
                    .select(qArticle.count())
                    .from(qArticle)
                    .where(qArticle.artCate.eq(catetype))
                    .fetchOne();


        // 페이징 처리를 위해 page 객체 리턴
        return new PageImpl<Tuple>(content, pageable, total);
    }


}