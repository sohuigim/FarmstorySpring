package com.farmstory.dto;

import com.farmstory.entity.Point;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDTO {
    private int pointNo;
    private String userId;
    private int pointValue;
    private String pointDesc;

    @CreationTimestamp
    private String pointDate;


    public Point toEntity() {
        return Point.builder()
                .pointNo(pointNo)
                .userId(userId)
                .pointValue(pointValue)
                .pointDesc(pointDesc)
                .pointDate(pointDate)
                .build();
    }

}
