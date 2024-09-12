package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRegisterController {

    @GetMapping("user/UserRegister")
    public String UserRegister(){
        return "user/UserRegister";
    }
}
