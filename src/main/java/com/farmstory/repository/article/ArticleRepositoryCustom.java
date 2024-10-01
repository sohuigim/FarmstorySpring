package com.farmstory.repository.article;

import com.farmstory.dto.pageDTO.ArticlePageRequestDTO;
import com.farmstory.entity.Article;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom  {

    public List<Article> selectArticles (String cate);
    public Article selectArticle (int no);
    public Page<Tuple> selectArticleAllForList(ArticlePageRequestDTO articlePageRequestDTO , Pageable pageable, String catetype);
}