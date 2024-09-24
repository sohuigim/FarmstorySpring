package com.farmstory.repository.article;


import com.farmstory.dto.ArticleDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.QArticle;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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


}