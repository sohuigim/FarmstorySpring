package com.farmstory.dto;

import com.farmstory.entity.Article;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    private LocalDate artRdate;
    private String artNick;
    @Builder.Default
    private List<FileDTO> fileList=new ArrayList<>();
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
