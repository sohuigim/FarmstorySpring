package com.farmstory.controller.market;

import com.farmstory.dto.ProductDTO;
import com.farmstory.entity.Product;
import com.farmstory.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MarketListController {

    private final ProductService productService;

    @GetMapping("/market/MarketList")
    public String MarketList(
            @RequestParam(defaultValue = "0") int page, // 시작 페이지 번호
            @RequestParam(defaultValue = "5") int size,
            Model model) {



        Pageable pageable = PageRequest.of(page, size, Sort.by("prodNo").ascending());
        Page<Product> productPage = productService.getPagedProducts(pageable);
        model.addAttribute("page", productPage);

        return "/market/MarketList";
    }


}
