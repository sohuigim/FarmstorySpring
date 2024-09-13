package com.farmstory.entity;

import com.farmstory.dto.OrderDTO;
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
@Table(name = "order")
public class Order {
    @Id
    private int orderNo;
    private String orderUserId;
    private int orderTotalPrice;
    private String orderAddr;
    private int orderItemDiscount;

    @CreationTimestamp
    private String orderDate;
    private String orderReceiveName;
    private String orderReceiveHp;
    private String orderMemo;
    private String orderSendHp;
    private String orderSendName;

    public OrderDTO toDTO(){
        return OrderDTO.builder()
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
