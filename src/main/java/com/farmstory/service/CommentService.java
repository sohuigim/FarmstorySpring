package com.farmstory.service;

import com.farmstory.dto.CommentDTO;
import com.farmstory.entity.Comment;
import com.farmstory.entity.User;
import com.farmstory.repository.CommentRepository;
import com.farmstory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CommentDTO insertComment(CommentDTO commentDTO) {
        // 댓글 엔티티 변환
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        // db에 있는 유저 가져옴
//        User user = userRepository.findById(commentDTO.getUserUid()).orElseThrow(() -> new RuntimeException("유저가 없습니다."));

        // 댓글에 유저 등록
//        comment.registerUser(user);
        log.info(comment);
        // 댓글 생성
        commentRepository.save(comment);
        // dto 변환 후 반환
        CommentDTO dto = modelMapper.map(comment, CommentDTO.class);
//        dto.setNick(user.getUserNick());
        return dto;
    }

    public List<CommentDTO> selectComment() {
        return null;
    }

    public CommentDTO selectComment(int no) {
        return null;

    }

    public void updateComment(CommentDTO commentDTO) {

    }

    public void deleteComment(int no) {

    }
}