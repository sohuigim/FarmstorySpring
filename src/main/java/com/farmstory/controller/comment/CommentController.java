package com.farmstory.controller.comment;

import com.farmstory.dto.CommentDTO;
import com.farmstory.entity.Article;
import com.farmstory.entity.Comment;
import com.farmstory.entity.User;
import com.farmstory.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private User user = new User();
    private Article article = new Article();


    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> write(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        String regip = request.getRemoteAddr();
        commentDTO.setCommentRegIp(regip);
        log.info(commentDTO);

        CommentDTO savedComment = commentService.insertComment(commentDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("comment", savedComment);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestParam("articleNo") int articleNo,
                                                             @RequestParam("commentNo") int commentNo) {
        int result = commentService.deleteComment(articleNo, commentNo);

        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "댓글이 성공적으로 삭제되었습니다.");
        } else {
            response.put("status", "fail");
            response.put("message", "댓글 삭제에 실패했습니다.");
        }

        return ResponseEntity.ok(response);
    }


    // 댓글 수정
    @PutMapping("/modify")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO) {
        log.info(commentDTO);
        commentService.updateComment(commentDTO);

       return ResponseEntity.ok().build();
    }


}
