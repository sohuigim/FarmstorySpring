package com.farmstory.controller.article.crop;

import com.farmstory.dto.ArticleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CropController {

    //농작물이야기 // 메인
//    @GetMapping("/crop/CropStory")
//    @GetMapping("/crop/CropGarden")
//    @GetMapping("/crop/CropReturnfarm")

    @GetMapping("/crop/{cate}")
    public String cropStory(@PathVariable String cate,  Model model) {
        String str1 = "";
        if(cate.equals("CropStory")){str1 = "b201";}
        else if(cate.equals("CropGarden")){str1 = "b202";}
        else if(cate.equals("CropReturnfarm")){str1 = "b203";}
        model.addAttribute("str1", str1);

        System.out.println(cate);

        System.out.println(str1);
        return "/crop/"+cate;
    }



    //글쓰기
    @GetMapping("/crop/{cate}/CropWrite")
    public String CropWrite(Model model, @PathVariable String cate) {
        String str1 = "";
        if (cate.equals("CropStory")){str1 = "b201";}
        else if (cate.equals("CropGarden")) {str1 = "b202";}
        else if (cate.equals("CropReturnfarm")) {str1 = "b203";}
        model.addAttribute("str1", str1);
        model.addAttribute("artCate", cate);
        return "/crop/talk/CropWrite";
    }
//    // 글쓰기(기능)
//    @PostMapping("/croptalk/write")
//    public String croptalkWrite(@ModelAttribute ArticleDTO articleDTO, HttpServletRequest request) {

    @PostMapping("/crop/CropWrite")
    public String CropWrite(@ModelAttribute ArticleDTO articleDTO) {

        System.out.println("---------");
        System.out.println(articleDTO.toString());
        System.out.println("---------");

        // db제출

        String cate = "";
        if (articleDTO.getArtCate().equals("CropStory")){ cate = "CropStory";}
        else if (articleDTO.getArtCate().equals("CropGarden")){ cate = "CropGarden";}
        else if (articleDTO.getArtCate().equals("CropReturnfarm")){ cate = "CropReturnfarm";}

        return "/crop/"+cate;
    }

    //글보기
    @GetMapping("/crop/{cate}/CropView")
    public String CropStoryView(Model model, @PathVariable String cate) {
        String str1 = "";
        System.out.println(cate);
        if (cate.equals("CropStory")) {str1 = "b201";}
        else if (cate.equals("CropGarden")) {str1 = "b202";}
        else if (cate.equals("CropReturnfarm")) {str1 = "b203";}
        model.addAttribute("str1", str1);
        return "/crop/talk/CropView";
    }

    //글수정
    @GetMapping("/crop/{cate}/CropModify")
    public String CropStoryModify(Model model, @PathVariable String cate) {
        String str1 = "";
        System.out.println(cate);
        if (cate.equals("CropStory")) {str1 = "b201";}
        else if (cate.equals("CropGarden")) {str1 = "b202";}
        else if (cate.equals("CropReturnfarm")) {str1 = "b203";}
        model.addAttribute("str1", str1);
        return "/crop/talk/CropModify";
    }
}