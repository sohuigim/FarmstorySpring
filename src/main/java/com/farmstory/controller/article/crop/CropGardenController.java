package com.farmstory.controller.article.crop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CropGardenController {

    //텃밭가꾸기
    @GetMapping("/crop/CropGarden")
    public String CropGarden() {
        return "/crop/CropGarden";
    }


}