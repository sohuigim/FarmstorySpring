package com.farmstory.entity;

import com.farmstory.dto.CommentDTO;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNo;
    private int artNo;
    private String commentRegIp;
    private String content;

    @CreationTimestamp
    private String commentRegDate;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User user;

    public CommentDTO toDTO() {
        return CommentDTO.builder()
                .commentNo(commentNo)
                .artNo(artNo)
                .commentRegIp(commentRegIp)
                .commentRegDate(commentRegDate)
                .build();
    }

    public void registerUser(User user) {
        this.user = user;
    };
}
