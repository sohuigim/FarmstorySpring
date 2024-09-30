package com.farmstory.controller.comment;

import com.farmstory.dto.CommentDTO;
import com.farmstory.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/write")
    public ResponseEntity<CommentDTO> write(@RequestBody CommentDTO commentDTO, HttpServletRequest request){

        String regip = request.getRemoteAddr();
        commentDTO.setCommentRegIp(regip);
        log.info(commentDTO);

        CommentDTO dto = commentService.insertComment(commentDTO);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @GetMapping("/comment/delete")
    public void delete(int no){

        commentService.deleteComment(no);

    }

}
