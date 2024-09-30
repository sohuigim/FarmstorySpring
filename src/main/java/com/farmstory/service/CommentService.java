package com.farmstory.service;

import com.farmstory.dto.CommentDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.Comment;
import com.farmstory.repository.CommentRepository;
import com.farmstory.repository.UserRepository;
import com.farmstory.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public CommentDTO insertComment(CommentDTO commentDTO) {
        // 댓글 엔티티 변환
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        Article article = articleRepository.findById(commentDTO.getArtNo()).orElseThrow(() -> new RuntimeException());
        // db에 있는 유저 가져옴
//        User user = userRepository.findById(commentDTO.getUserUid()).orElseThrow(() -> new RuntimeException("유저가 없습니다."));

        // 댓글에 유저 등록
//        comment.registerUser(user);
        comment.registerArticle(article);
        log.info(comment);
        // 댓글 생성
        commentRepository.save(comment);


        // dto 변환 후 반환
        CommentDTO dto = modelMapper.map(comment, CommentDTO.class);
//        dto.setNick(user.getUserNick());

        log.info("dto : " +dto);

        return dto;
    }

    public List<CommentDTO> selectComments() {

        List<Comment> comments = commentRepository.findAll();

        List<CommentDTO> commentDTOs = comments.stream()
        .map(comment -> CommentDTO.builder()
                .commentNo(comment.getCommentNo())
                .artNo(comment.getArticle().getArtNo()) // 필요한 경우 artNo도 매핑
                .userUid(comment.getUser().getUserUid())
                .commentRegIp(comment.getCommentRegIp()) // 추가 매핑 필요시
                .content(comment.getContent())
                .nick(comment.getUser().getUserNick()) // 필요시 닉네임 추가
                .commentRegDate(comment.getCommentRegDate())
                .build())
                .collect(Collectors.toList());

        return commentDTOs;
    }

    public CommentDTO selectComment(int no) {
        return null;

    }

    public void updateComment(CommentDTO commentDTO) {

    }

    public void deleteComment(int no) {
        commentRepository.delete(commentRepository.findById(no).orElseThrow(() -> new RuntimeException()));
    }

    public List<Comment> selectCommentByArtNo(int artNo){

      return   commentRepository.findAllByArticleArtNo(artNo);
    }
}