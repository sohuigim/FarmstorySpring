package com.farmstory.controller.article.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityCsController {

    // 커뮤니티 - 고객문의
    @GetMapping("/community/CommunityCs")
    public String CommunityCs() {
        return "/community/CommunityCs";
    }

}