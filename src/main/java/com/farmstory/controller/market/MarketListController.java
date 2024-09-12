package com.farmstory.controller.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketListController {

    @GetMapping("/market/MarketList")
    public String MarketList(){
        return "/market/MarketList";
    }


}
