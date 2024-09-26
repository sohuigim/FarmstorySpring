package com.farmstory.service;


import com.farmstory.dto.ArticleDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.File;
import com.farmstory.entity.QArticle;
import com.farmstory.repository.article.ArticleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;
    private JPAQueryFactory queryFactory;
    private QArticle article = QArticle.article;

    public ArticleService(ArticleRepository articleRepository) {this.articleRepository = articleRepository; }

    public Article saveArticle(ArticleDTO articleDTO) {

        // DTO를 엔티티로 변환
        Article article = articleDTO.toEntity();
        System.out.println("Article dto : "+ articleDTO.toString());
        System.out.println("article  : "+ article.toString());

        return articleRepository.save(article);
    }

    public void registerArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toEntity();
        articleRepository.save(article);
    }

    public List<Article> selectArticles(String cate) {
        return articleRepository.selectArticles(cate);
    }

    public Article selectArticle(int artNo) {
        return articleRepository.selectArticle(artNo);
    }


    public ArticleDTO getArticle(int artNo) {
        Optional<Article> articleOpt = articleRepository.findById(artNo);
        ArticleDTO articleDTO = articleOpt.map(Article::toDTO).orElse(null);
        for (File file : articleOpt.get().getFiles()) {
            articleDTO.getFileList().add(file.toDTO());
        }
        return articleDTO;
    }

    public void deleteArticle(int artNo) {
        articleRepository.deleteById(artNo);
    }

    public void updateArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toEntity();
        articleRepository.save(article);
    }


}
