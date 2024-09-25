package com.farmstory.repository.article;

import com.farmstory.entity.Article;

import java.util.List;

public interface ArticleRepositoryCustom  {

    public List<Article> selectArticles (String cate);
    public Article selectArticle (int no);

}