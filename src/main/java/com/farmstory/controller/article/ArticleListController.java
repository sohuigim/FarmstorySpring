package com.farmstory.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleListController {

    @GetMapping("/event/EventList")
    public String EventList(){
        return "/event/EventList";
    }
}
