package com.farmstory.dto;

import com.farmstory.entity.OrderBills;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBillsDTO {
    private int billsItemNo;
    private int OrderNo;
    private int ProdNo;
    private int billsItemPrice;
    private int billsItemDiscount;
    private int billsItemCount;


    public OrderBills toEntity() {
        return OrderBills.builder()
                .billsItemNo(billsItemNo)
                .OrderNo(OrderNo)
                .ProdNo(ProdNo)
                .billsItemPrice(billsItemPrice)
                .billsItemDiscount(billsItemDiscount)
                .billsItemCount(billsItemCount)
                .build();
    }
}
