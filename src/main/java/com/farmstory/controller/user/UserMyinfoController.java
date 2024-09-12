package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMyinfoController {

    @GetMapping("user/UserMyinfo")
    public String UserMyinfo(){
        return "user/UserMyinfo";
    }
}
