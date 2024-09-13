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

    // 글쓰기
    @GetMapping("/community/CommunityWrite")
    public String CommunityWrite(){
        return "/community/CommunityWrite";
    }

    // 글수정
    @GetMapping("/community/CommunityModify")
    public String CommunityModify(){
        return "/community/CommunityModify";
    }

    // 글보기
    @GetMapping("/community/CommunityView")
    public String CommunityView(){
        return "/community/CommunityView";
    }
}