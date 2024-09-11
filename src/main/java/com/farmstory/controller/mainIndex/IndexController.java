package com.farmstory.controller.mainIndex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/market/marketList"})
    public String marketList(){
        return "/market/MarketList";
    }
}
