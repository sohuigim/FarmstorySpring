package com.farmstory.entity;

import com.farmstory.dto.FileDTO;
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
@Table(name = "file")
public class File {

    @Id
    private int fileNo;
    private int artNo;
    private String fileoName;
    private String filesName;
    private int fileDownload;

    @CreationTimestamp
    private String fileRdate;
    private String filePath;


    public FileDTO toDTO() {
        return FileDTO.builder()
                .fileNo(fileNo)
                .artNo(artNo)
                .fileoName(fileoName)
                .filesName(filesName)
                .fileDownload(fileDownload)
                .filePath(filePath)
                .build();
    }
}
