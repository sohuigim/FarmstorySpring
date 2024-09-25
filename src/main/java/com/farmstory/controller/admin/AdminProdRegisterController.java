package com.farmstory.controller.admin;

import com.farmstory.dto.ProductDTO;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminProdRegisterController {

    private final ProductService productService;
    @GetMapping("/admin/ProductRegister")
    public String AdminProdRegister() {
        return "/admin/product/ProductRegister";
    }
    @PostMapping("/admin/ProductRegister")
    public String AdminProdRegister(ProductDTO productDTO) {

        return "redirect:/admin/product/ProductRegister";
    }

}
