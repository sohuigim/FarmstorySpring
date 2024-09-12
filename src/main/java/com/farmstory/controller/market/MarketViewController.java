package com.farmstory.controller.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketViewController {

    @GetMapping("/market/MarketView")
    public String MarketView(){
        return "/market/MarketView";
    }

}
