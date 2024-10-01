package com.farmstory.service;


import com.farmstory.dto.*;
import com.farmstory.entity.Article;

import com.farmstory.entity.FileEntity;

import com.farmstory.entity.QArticle;
import com.farmstory.repository.CommentRepository;
import com.farmstory.repository.FileRepository;
import com.farmstory.repository.article.ArticleRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;
    private JPAQueryFactory queryFactory;
    private QArticle article = QArticle.article;


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
        List<FileEntity> files = fileRepository.findAllByArticle(articleOpt.get());
        for (FileEntity file : files) {
            articleDTO.getFileList().add(file.toDTO());
        }
        return articleDTO;
    }

    public ArticlePageResponseDTO selectProductAll(ArticlePageRequestDTO articlePageRequestDTO, String catetype){
        Pageable pageable = articlePageRequestDTO.getPageable("artNo");

        articlePageRequestDTO.setCate(catetype);

        Page<Tuple> pageArticle = articleRepository.selectArticleAllForList(articlePageRequestDTO, pageable, catetype);

        List<ArticleDTO> articleList = pageArticle.getContent().stream().map(tuple -> {

            Article article = tuple.get(0, Article.class);

            return modelMapper.map(article, ArticleDTO.class);

        }).toList();

        int total = (int) pageArticle.getTotalElements();

        return ArticlePageResponseDTO.builder()
                .articlePageRequestDTO(articlePageRequestDTO)
                .dtoList(articleList)
                .total(total)
                .build();
    }

    @Transactional
    public void deleteArticle(int artNo) {
        articleRepository.deleteById(artNo);
        commentRepository.deleteById(artNo);
    }

    public void updateArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toEntity();
        articleRepository.save(article);
    }


}
