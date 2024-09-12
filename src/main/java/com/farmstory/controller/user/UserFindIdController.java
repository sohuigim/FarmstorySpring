package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserFindIdController {

    @GetMapping("user/UserFindId")
    public String UserFindId(){
        return "user/UserFindId";
    }
}
