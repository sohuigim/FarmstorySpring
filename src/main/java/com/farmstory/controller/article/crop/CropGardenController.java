package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropGardenController {

    //텃밭가꾸기
    @GetMapping("/crop/CropGarden")
    public String CropGarden(Model model) {
        String str1 = "b202";
        model.addAttribute("str1", str1);
        return "/crop/CropGarden";
    }


}