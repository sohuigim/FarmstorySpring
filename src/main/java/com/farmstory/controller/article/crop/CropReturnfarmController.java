package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropReturnfarmController {

    //귀농학교
    @GetMapping("/crop/CropReturnfarm")
    public String CropReturnfarm(Model model) {
        String str1 = "b203";
        model.addAttribute("str1", str1);
        return "/crop/CropReturnfarm";
    }


}
