package com.farmstory.controller.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketOrderController {

    @GetMapping("/market/MarketCart")
    public String MarketCart(){
        return "/market/MarketCart";
    }

    @GetMapping("/market/MarketOrder")
    public String MarketOrder(){
        return "/market/MarketOrder";
    }

}
