package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropStroyController {

    //농작물이야기
    @GetMapping("/crop/CropStory")
    public String cropStory() {
        return "/crop/CropStory";
    }

    //글쓰기
    @GetMapping("/crop/talk/CropWrite")
    public String CropStoryWriter() {
        return "/crop/talk/CropWrite";
    }

    //글보기
    @GetMapping("/crop/talk/CropView")
    public String CropStoryView() {
        return "/crop/talk/CropView";
    }

    //글수정
    @GetMapping("/crop/talk/CropModify")
    public String CropStoryModify() {
        return "/crop/talk/CropModify";
    }

}