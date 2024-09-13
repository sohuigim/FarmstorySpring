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