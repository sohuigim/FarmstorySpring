package com.farmstory.service;


import com.farmstory.dto.*;
import com.farmstory.dto.pageDTO.ArticlePageRequestDTO;
import com.farmstory.dto.pageDTO.ArticlePageResponseDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.QArticle;
import com.farmstory.repository.CommentRepository;
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
    private final CommentRepository commentRepository;
    private final FileService fileService;

    private final ModelMapper modelMapper;
    private JPAQueryFactory queryFactory;
    private QArticle article = QArticle.article;



    public int insertArticle(ArticleDTO articleDTO) {

        // ModelMapper를 이용해서 DTO를 Entity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(articleDTO.toString());

        // 저장
        Article savedArticle = articleRepository.save(article);

        // 저장된 글번호 리턴
        return savedArticle.getArtNo();
    }




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

    public ArticleDTO selectArticle(int artNo) {
        Article article = articleRepository.findById(artNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        ArticleDTO articleDTO = article.toDTO();

        List<FileDTO> fileList = fileService.getFilesByArtNo(artNo);
        articleDTO.setFileList(fileList);

        return articleDTO;
    }


    public ArticleDTO getArticle(int artNo) {
        Optional<Article> optArticle = articleRepository.findById(artNo);
        if(optArticle.isPresent()){
            Article article = optArticle.get();
            log.info(article.toString());

            ArticleDTO dto = modelMapper.map(article, ArticleDTO.class);
            return dto;
        }

        return null;
    }

    public ArticlePageResponseDTO selectArticleAll(ArticlePageRequestDTO articlePageRequestDTO, String catetype){
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
        fileService.deleteFile(artNo);

        commentRepository.deleteById(artNo);

        articleRepository.deleteById(artNo);


    }

    public void updateArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toEntity();
        articleRepository.save(article);
    }


}
