package com.farmstory.service;


import com.farmstory.dto.ArticleDTO;
import com.farmstory.entity.Article;
import com.farmstory.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

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

    public List<ArticleDTO> getAllArticle() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(Article::toDTO)
                .collect(Collectors.toList());
    }

    public ArticleDTO getArticle(String artNo) {
        Optional<Article> articleOpt = articleRepository.findById(artNo);
        return articleOpt.map(Article::toDTO).orElse(null);
    }

    public void deleteArticle(String artNo) {
        articleRepository.deleteById(artNo);
    }

    public void updateArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toEntity();
        articleRepository.save(article);
    }


}
