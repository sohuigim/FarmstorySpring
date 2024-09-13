package com.farmstory.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProdRegisterController {

    @GetMapping("/admin/ProductRegister")
    public String AdminProdRegister() {
        return "/admin/product/ProductRegister";
    }

}
