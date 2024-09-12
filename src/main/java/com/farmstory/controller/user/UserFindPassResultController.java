package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserFindPassResultController {

    @GetMapping("user/UserFindPassResult")
    public String UserFindPassResult(){
        return "user/UserFindPassResult";
    }
}
