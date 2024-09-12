package com.farmstory.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTermsController {

    @GetMapping("user/UserTerms")
    public String UserUserTerms(){
        return "user/UserTerms";
    }
}
