package com.farmstory.controller.article.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityDietController {

    // 커뮤니티 - 오늘의식단
    @GetMapping("/community/CommunityDiet")
    public String CommunityDiet() {
        return "/community/CommunityDiet";
    }

}