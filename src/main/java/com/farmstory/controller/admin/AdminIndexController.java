package com.farmstory.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminIndexController {

    @GetMapping("/admin/AdminIndex")
    public String AdminIndex() {
        return "/admin/AdminIndex";
    }

    @GetMapping("/admin/user/UserList")
    public String AdminUser() {
        return "/admin/user/UserList";
    }

    @GetMapping("/admin/product/ProductList")
    public String AdminProd() {
        return "/admin/product/ProductList";
    }

    @GetMapping("/admin/product/ProductRegister")
    public String AdminProdRegister() {
        return "/admin/product/ProductRegister";
    }

    @GetMapping("/admin/order/OrderList")
    public String AdminOrder() {
        return "/admin/order/OrderList";
    }

}
