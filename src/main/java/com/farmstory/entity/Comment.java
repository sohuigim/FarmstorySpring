package com.farmstory.entity;

import com.farmstory.dto.CommentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // 엔티티 객체 정의
@Table(name = "comment")
public class Comment {

    @Id
    private int commentNo;
    private int artNo;
    private String userUid;
    private String commentRegIp;

    @CreationTimestamp
    private String commentRegDate;

    public CommentDTO toDTO() {
        return CommentDTO.builder()
                .commentNo(commentNo)
                .artNo(artNo)
                .userUid(userUid)
                .commentRegIp(commentRegIp)
                .commentRegDate(commentRegDate)
                .build();
    }
}
