package com.farmstory.dto;

import com.farmstory.entity.Article;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ArticleDTO {

    private int artNo;
    private int artComment;
    private String artCate;
    private String artTitle;
    private String artContent;
    private String artWriter;
    private int artFile;
    private int artHit;

    private String artRegip;

    @CreationTimestamp
    private LocalDateTime artRdate;
    private String artNick;

    public Article toEntity(){
        return Article.builder()
                .artNo(artNo)
                .artComment(artComment)
                .artCate(artCate)
                .artTitle(artTitle)
                .artContent(artContent)
                .artWriter(artWriter)
                .artFile(artFile)
                .artHit(artHit)
                .artRegip(artRegip)
                .artRdate(artRdate)
                .artNick(artNick)
                .build();

    }

}
