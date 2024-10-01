package com.farmstory.controller.market;

import com.farmstory.dto.CartDTO;
import com.farmstory.dto.ProductDTO;
import com.farmstory.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MarketViewController {

    private final ProductService productService;

    @GetMapping("/market/MarketView")
    public String MarketView(@RequestParam("pNo") int pNo, Model model) {

        ProductDTO productDTO = productService.selectProduct(pNo);

        model.addAttribute("product", productDTO);
        return "/market/MarketView";
    }

}

