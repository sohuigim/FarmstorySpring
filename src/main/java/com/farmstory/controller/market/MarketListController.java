package com.farmstory.controller.market;

import com.farmstory.dto.ProductDTO;
import com.farmstory.service.Market.MarketListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MarketListController {

    private final MarketListService marketListService;

    @GetMapping("/market/MarketList")
    public String MarketList(Model model) {

        List<ProductDTO> products = marketListService.selectProducts();
        log.info(products);
        model.addAttribute("products", products);

        return "/market/MarketList";
    }

}
