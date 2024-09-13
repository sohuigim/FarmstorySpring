package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropStroyController {

    //농작물이야기 // 메인
    @GetMapping("/crop/CropStory")
    public String cropStory(Model model) {
        String str1 = "b201";
        model.addAttribute("str1", str1);
        return "/crop/CropStory";
    }

    //글쓰기
    @GetMapping("/crop/talk/CropWrite")
    public String CropStoryWriter(Model model) {
        String str1 = "b201";
        model.addAttribute("str1", str1);

        return "/crop/talk/CropWrite";
    }

    //글보기
    @GetMapping("/crop/talk/CropView")
    public String CropStoryView(Model model) {
        String str1 = "b201";
        model.addAttribute("str1", str1);
        return "/crop/talk/CropView";
    }

    //글수정
    @GetMapping("/crop/talk/CropModify")
    public String CropStoryModify(Model model) {
        String str1 = "b201";
        model.addAttribute("str1", str1);
        return "/crop/talk/CropModify";
    }

}