package com.farmstory.controller.admin;

import com.farmstory.dto.ProductDTO;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
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
        log.info("AdminProdRegister" + productDTO);
        productService.insertProduct(productDTO);
        return "redirect:/admin/ProductRegister";
    }

}
