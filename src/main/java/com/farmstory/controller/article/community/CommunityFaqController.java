package com.farmstory.controller.article.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityFaqController {

    // 커뮤니티 - 자주묻는질문
    @GetMapping("/community/CommunityFaq")
    public String CommunityFaq() {
        return "/community/CommunityFaq";
    }

 
}