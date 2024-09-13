package com.farmstory.controller.article.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityNoticeController {

    // 커뮤니티 - 공지사항
    @GetMapping("/community/CommunityNotice")
    public String CommunityNotice() {
        return "/community/CommunityNotice";
    }

    // 위와 같이 @Getmapping 으로 글쓰기에 대한 화면이 나타나게끔 해야함
    @GetMapping("/community/article/ArticleWrite")
    public String CommunityWrite() {
        return "/community/article/ArticleWrite";
    }

}