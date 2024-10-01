package com.farmstory.dto;

import com.farmstory.entity.Order;
import com.farmstory.entity.Product;
import com.farmstory.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private int orderNo;
    private String orderuserUid;
    private int prodNo;
    private String prodName;
    private String orderZip;
    private String orderAddr1;
    private String orderAddr2;
    private int orderItemDiscount;
    private int orderPay;

    private List<ProductDTO> productDTO;

    @CreationTimestamp
    private LocalDateTime orderDate;
    private String Date;
    private String timeDate;
    private String orderReceiveName;
    private String orderReceiveHp;
    private String orderMemo;
    private String orderSendHp;
    private String orderSendName;

    private int orderTotalPrice;
    private int orderPrice;
    private int orderStock;
    private int orderDeliveryFee;
    private int orderDiscount;
    private int ordersGroup;
    private int orderUsePoint;


    public Order toEntity(Product product) {
        return Order.builder()
                .orderNo(orderNo)
                .product(product) // Product는 따로 설정해야 함
                .orderAddr1(orderAddr1)
                .orderItemDiscount(orderItemDiscount)
                .orderDate(orderDate)
                .orderReceiveName(orderReceiveName)
                .orderReceiveHp(orderReceiveHp)
                .orderMemo(orderMemo)
                .orderSendHp(orderSendHp)
                .orderSendName(orderSendName)
                .orderPrice(orderPrice)
                .orderStock(orderStock)
                .orderDeliveryFee(orderDeliveryFee)
                .orderDiscount(orderDiscount)
                .ordersGroup(ordersGroup)
                .build();
    }
}