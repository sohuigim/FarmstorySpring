package com.farmstory.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProdListController {

    @GetMapping("/admin/ProductList")
    public String AdminProd() {
        return "/admin/product/ProductList";
    }


}
