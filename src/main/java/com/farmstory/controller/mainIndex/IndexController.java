package com.farmstory.controller.mainIndex;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String index(Authentication authentication, Model model) {
        if(authentication !=null && authentication.isAuthenticated()){
            // 로그인 했을 경우

            String auth = authentication.getAuthorities().toString();

            String role = auth.replace("[", "")        // 대괄호 제거
                        .replace("]", "")       // 대괄호 제거
                        .replace("ROLE_", "");  // ROLE_ 접두사 제거

            model.addAttribute("isAuthenticated", true);
             model.addAttribute("role",role);

        }else {
            // 로그인 안했을 경우
            model.addAttribute("isAuthenticated", false);
        }
        return "/index";
    }

}
