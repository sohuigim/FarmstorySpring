package com.farmstory.entity;

import com.farmstory.dto.OrderBillsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // 엔티티 객체 정의
@Table(name = "orderbills")
public class OrderBills {

    @Id
    private int billsItemNo;
    private int OrderNo;
    private int ProdNo;
    private int billsItemPrice;
    private int billsItemDiscount;
    private int billsItemCount;

    public OrderBillsDTO toDTO() {
        return OrderBillsDTO.builder()
                .billsItemNo(billsItemNo)
                .OrderNo(OrderNo)
                .ProdNo(ProdNo)
                .billsItemPrice(billsItemPrice)
                .billsItemDiscount(billsItemDiscount)
                .billsItemCount(billsItemCount)
                .build();
    }
}
