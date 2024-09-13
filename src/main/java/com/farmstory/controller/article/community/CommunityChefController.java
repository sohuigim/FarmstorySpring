package com.farmstory.controller.article.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityChefController {

    // 커뮤니티 - 나도요리사
    @GetMapping("/community/CommunityChef")
    public String CommunityChef() {
        return "/community/CommunityChef";
    }

}