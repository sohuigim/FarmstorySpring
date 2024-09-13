package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMyinfoController {

    @GetMapping("user/UserMyinfo")
    public String UserMyinfo(){
        return "user/UserMyinfo";
    }

    @GetMapping("user/UserMyinfoCart")
    public String UserMyinfoCart(){
        return "user/UserMyinfoCart";
    }

    @GetMapping("user/UserMyinfoOrder")
    public String UserMyinfoOrder(){
        return "user/UserMyinfoOrder";
    }
}
