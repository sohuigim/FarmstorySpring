package com.farmstory.controller.admin;

import com.farmstory.dto.ProductDTO;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminProdListController {

    private final ProductService productService;
    @GetMapping("/admin/ProductList")
    public String AdminProd(Model model) {
        List<ProductDTO> productDTO = productService.selectProducts();
        log.info(productDTO);
        model.addAttribute("productDTOs",productDTO);
        return "/admin/product/ProductList";
    }


}
