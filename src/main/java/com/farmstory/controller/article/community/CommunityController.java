package com.farmstory.controller.article.community;

import com.farmstory.dto.ArticleDTO;
import com.farmstory.entity.Article;
import com.farmstory.repository.article.ArticleRepository;
import com.farmstory.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommunityController {
    private final ArticleService articleService;
    private ArticleRepository articleRepository;

    public CommunityController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/community/{cate}")
    public String CommunityNoti(@PathVariable String cate, Model model) {
        String str1 = "";
        if(cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if(cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if(cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if(cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if(cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        model.addAttribute("str1", str1);


        System.out.println("cate : "+ cate);
        System.out.println("cate : "+ str1);
        List<Article> articles = articleService.selectArticles(cate);
        model.addAttribute("articles", articles);
        System.out.println(articles);

        return "/community/" + cate;
    }

    //글쓰기
    @GetMapping("/community/{cate}/CommunityWrite")
    public String CommunityWrite(Model model, @PathVariable("cate") String cate) {

        String str1 = "";
        if(cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if(cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if(cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if(cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if(cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        model.addAttribute("str1", str1);
        model.addAttribute("artCate", cate);
        System.out.println("Model Map: " + model.asMap());
        System.out.println(str1);
        return "/community/talk/CommunityWrite";
    }

    //글쓰기
    @PostMapping("/community/CommunityWrite")

    public String CommunityWrite(Model model, @ModelAttribute ArticleDTO articleDTO, @RequestParam String artCate) {

        System.out.println("ArticleDTO: " + articleDTO);
        System.out.println("Received artCate: " + artCate);  // 받아온 artCate 확인

        // DB에 저장
        articleService.saveArticle(articleDTO);

        System.out.println("글이 성공적으로 저장되었습니다.");

        // db제출
        model.addAttribute("str1", "b101");
        return "redirect:/community/CommunityNotice";

    }

    //글보기
    @GetMapping("/community/{cate}/CommunityView")
    public String CommunityView(Model model, @PathVariable("cate") String cate) {
        String str1 = "";
        if(cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if(cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if(cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if(cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if(cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        model.addAttribute("str1", str1);

        System.out.println(str1);
        System.out.println(cate);
        return "/community/talk/CommunityView";
    }

    //글수정
    @GetMapping("/community/{cate}/CommunityModify")
    public String CommunityViewModify(Model model, @PathVariable String cate) {
        String str1 = "";
        if(cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if(cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if(cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if(cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if(cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        model.addAttribute("str1", str1);
        return "/community/talk/CommunityModify";
    }
}