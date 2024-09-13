package com.farmstory.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminOrderListController {

    @GetMapping("/admin/OrderList")
    public String AdminOrder() {
        return "/admin/order/OrderList";
    }
}
