package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropReturnfarmController {

    //귀농학교
    @GetMapping("/crop/CropReturnfarm")
    public String CropReturnfarm() {
        return "/crop/CropReturnfarm";
    }


}
