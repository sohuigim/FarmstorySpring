package com.farmstory.controller.market;

import com.farmstory.dto.MarketPageRequestDTO;
import com.farmstory.dto.MarketPageResponseDTO;
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
    public String MarketList(Model model, MarketPageRequestDTO marketPageRequestDTO, @RequestParam(value = "catetype", required=false) Integer catetype) {

        List<ProductDTO> products = productService.selectProducts();
        model.addAttribute("products", products);

        if(catetype == null) {
            catetype = 0;
        }
        MarketPageResponseDTO marketPageResponseDTO = productService.selectProductAll(marketPageRequestDTO, catetype);
        model.addAttribute("marketPageResponseDTO", marketPageResponseDTO);
        return "/market/MarketList";
    }


}
