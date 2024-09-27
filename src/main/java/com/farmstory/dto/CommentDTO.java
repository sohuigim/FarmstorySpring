package com.farmstory.dto;

import com.farmstory.entity.Comment;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private int commentNo;
    private int artNo;
    private String userUid;
    private String commentRegIp;
    private String content;
    private String nick;

    @CreationTimestamp
    private String commentRegDate;


    public Comment toEntity() {
        return Comment.builder()
                .commentNo(commentNo)
                .commentRegIp(commentRegIp)
                .commentRegDate(commentRegDate)
                .build();
    }
}
