package com.farmstory.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MarketPageResponseDTO {

    private List<ProductDTO> dtoList;

    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private int cateType;

    @Builder
    public MarketPageResponseDTO(MarketPageRequestDTO marketPageRequestDTO, List<ProductDTO> dtoList, int total) {
        this.pg = marketPageRequestDTO.getPg();
        this.size = marketPageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.cateType = marketPageRequestDTO.getCateType();

        this.startNo = total - ((pg -1) * size);
        this.end = (int) (Math.ceil(this.pg / 5.0)) * 5;
        this.start = this.end -4;

        int last = (int) (Math.ceil(total / (double)size));
        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }



}
