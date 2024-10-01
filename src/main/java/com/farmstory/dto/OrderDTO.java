package com.farmstory.dto;

import com.farmstory.entity.Order;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private int orderNo;
    private String orderUserId;
    private int orderTotalPrice;
    private String orderAddr;
    private int orderItemDiscount;

    @CreationTimestamp
    private LocalDateTime orderDate;
    private String orderReceiveName;
    private String orderReceiveHp;
    private String orderMemo;
    private String orderSendHp;
    private String orderSendName;




    public Order toEntity() {
        return Order.builder()
                .orderNo(orderNo)
                .orderUserId(orderUserId)
                .orderTotalPrice(orderTotalPrice)
                .orderAddr(orderAddr)
                .orderItemDiscount(orderItemDiscount)
                .orderDate(orderDate)
                .orderReceiveName(orderReceiveName)
                .orderReceiveHp(orderReceiveHp)
                .orderMemo(orderMemo)
                .orderSendHp(orderSendHp)
                .orderSendName(orderSendName)
                .build();
    }
}
