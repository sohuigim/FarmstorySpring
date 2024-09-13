package com.farmstory.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleListController {

    //이벤트
    @GetMapping("/event/EventList")
    public String EventList(){
        return "/event/EventList";
    }

    //커뮤니티
    @GetMapping("/community/CommunityNotice")
    public String CommunityNotice(){
        return "/community/CommunityNotice";
    }





}
