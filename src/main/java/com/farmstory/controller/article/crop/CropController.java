package com.farmstory.controller.article.crop;

import com.farmstory.dto.ArticleDTO;

import com.farmstory.entity.Article;
import com.farmstory.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CropController {
    private final ArticleService articleService;

    public CropController(ArticleService articleService) {
        this.articleService = articleService;
    }


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
    public String CropWrite(Model model, @PathVariable("cate") String cate) {


        String str1 = "";
        if (cate.equals("CropStory")){str1 = "b201";}
        else if (cate.equals("CropGarden")) {str1 = "b202";}
        else if (cate.equals("CropReturnfarm")) {str1 = "b203";}


        System.out.println("Category: " + cate);  // cate 값 확인
        model.addAttribute("str1", str1);
        model.addAttribute("artCate", cate);
        System.out.println("Model Map: " + model.asMap());

        return "/crop/talk/CropWrite";
    }
//    // 글쓰기(기능)
//    @PostMapping("/croptalk/write")
//    public String croptalkWrite(@ModelAttribute ArticleDTO articleDTO, HttpServletRequest request) {

    @PostMapping("/crop/CropWrite")

    public String CropWrite(Model model, @ModelAttribute ArticleDTO articleDTO, @RequestParam String artCate) {

        System.out.println("ArticleDTO: " + articleDTO);
        System.out.println("Received artCate: " + artCate);  // 받아온 artCate 확인

        // DB에 저장
        articleService.saveArticle(articleDTO);

        System.out.println("글이 성공적으로 저장되었습니다.");

        // db제출
        model.addAttribute("str1", "b201");
        return "redirect:/crop/CropStory";

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