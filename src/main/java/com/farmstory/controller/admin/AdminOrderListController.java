package com.farmstory.controller.admin;

import com.farmstory.dto.OrderDTO;
import com.farmstory.dto.ProductDTO;
import com.farmstory.service.OrderService;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminOrderListController {

    private final OrderService orderService;

    @GetMapping("/admin/OrderList")
    public String AdminOrder(Model model) {
        List<OrderDTO> orderDTO = orderService.selectOrders();
        log.info("orderDTO" + orderDTO);
        model.addAttribute("orderDTOS", orderDTO);
        return "/admin/order/OrderList";
    }

    @DeleteMapping("/admin/OrderList/Delete")
    public ResponseEntity<?> AdminProdListDelete(@RequestBody List<String> orderIds) {
        try{
            log.info(orderIds);
            orderService.DeleteOrders(orderIds);
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("success",false));
        }
    }
}
