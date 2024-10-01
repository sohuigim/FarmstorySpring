package com.farmstory.service;

import com.farmstory.dto.OrderDTO;
import com.farmstory.entity.Order;
import com.farmstory.entity.Product;
import com.farmstory.repository.OrderRepository;
import com.farmstory.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public void insertOrder(OrderDTO orderDTO) {

//        Product product = productRepository.findById(orderDTO.getProdNo())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
//
//        // OrderDTO -> Order 엔티티로 변환할 때 Product 객체를 전달
//        Order entity = orderDTO.toEntity(product);
        orderRepository.save(orderDTO.toEntity());
    }

    public List<OrderDTO> selectOrders() {

//        List<Order> orders = orderRepository.findAll();
//
//        List<OrderDTO> orderDTOs = orders.stream()
//                .map(entity -> {
//                    OrderDTO dto = entity.toDTO();
//                    if (entity.getProduct() != null) {
//                        dto.setProdName(entity.getProduct().getProdName()); // Product 이름 설정
//                    }
//                    return dto;
//                })
//                .collect(Collectors.toList());
//
//        return orderDTOs;
        return null;
    }
    public void DeleteOrders(List<String> orderIds) {
        for(String id : orderIds) {
            try{
                int orderId = Integer.parseInt(id);
                orderRepository.deleteById(orderId);
            }catch (NumberFormatException e){
                System.out.println("Invalid Order ID format: " + id);
                throw new IllegalArgumentException("Invalid Order ID format: " + id);
            }
        }
    }
}

