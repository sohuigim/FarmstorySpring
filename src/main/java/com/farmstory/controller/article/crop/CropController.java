package com.farmstory.controller.article.crop;

import com.farmstory.dto.ArticleDTO;

import com.farmstory.entity.Article;
import com.farmstory.entity.Comment;
import com.farmstory.repository.article.ArticleRepository;
import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@Controller
@RequiredArgsConstructor
public class CropController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    //농작물이야기 // 메인
//    @GetMapping("/crop/CropStory")
//    @GetMapping("/crop/CropGarden")
//    @GetMapping("/crop/CropReturnfarm")

    @GetMapping("/crop/{cate}")
    public String cropStory(@PathVariable String cate, Model model) {
        String str1 = "";
        if (cate.equals("CropStory")) {
            str1 = "b201";
        } else if (cate.equals("CropGarden")) {
            str1 = "b202";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        model.addAttribute("str1", str1);


        List<Article> articles = articleService.selectArticles(cate);
        model.addAttribute("articles", articles);
        System.out.println(articles);

        return "/crop/" + cate;
    }

    //글쓰기
    @GetMapping("/crop/{cate}/CropWrite")
    public String CropWrite(Model model, @PathVariable("cate") String cate) {


        String str1 = "";
        if (cate.equals("CropStory")) {
            str1 = "b201";
        } else if (cate.equals("CropGarden")) {
            str1 = "b202";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b203";
        }


        model.addAttribute("str1", str1);
        model.addAttribute("artCate", cate);
        System.out.println("Model Map: " + model.asMap());
        System.out.println(str1);
        return "/crop/talk/CropWrite";
    }
//    // 글쓰기(기능)
//    @PostMapping("/croptalk/write")
//    public String croptalkWrite(@ModelAttribute ArticleDTO articleDTO, HttpServletRequest request) {

    @PostMapping("/crop/CropWrite")

    public String CropWrite(Model model, @ModelAttribute ArticleDTO articleDTO, String artCate) {

        String str1 = "";
        if (artCate.equals("CropStory")) {
            str1 = "b201";
        } else if (artCate.equals("CropGarden")) {
            str1 = "b202";
        } else if (artCate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        log.info("Received artCate: " + artCate);
        System.out.println("ArticleDTO: " + articleDTO);
        System.out.println("Received artCate: " + artCate);  // 받아온 artCate 확인

        // DB에 저장
        articleService.saveArticle(articleDTO);

        System.out.println("글이 성공적으로 저장되었습니다.");

        // db제출
        model.addAttribute("str1", "b201");
        return "redirect:/crop/" + artCate;

    }

    //글보기
    @GetMapping("/crop/{cate}/CropView/{artNo}")
    public String CropStoryView(Model model, @PathVariable("cate") String cate, @PathVariable("artNo") int artNo) {
        String str1 = "";
        if (cate.equals("CropStory")) {
            str1 = "b201";
        } else if (cate.equals("CropGarden")) {
            str1 = "b202";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        ArticleDTO articleDTO = articleService.getArticle(artNo);
        List<Comment> comments = commentService.selectCommentByArtNo(articleDTO.getArtNo());
        model.addAttribute(articleDTO);

        model.addAttribute("str1", str1);
        model.addAttribute("comments", comments);

        System.out.println(str1);
        System.out.println(cate);
        return "/crop/talk/CropView";
    }

    //글수정
    @GetMapping("/crop/{cate}/CropModify/{artNo}")
    public String CropStoryModify(Model model, @PathVariable String cate, @PathVariable("artNo") int artNo) {
        String str1 = "";
        System.out.println(cate);
        if (cate.equals("CropStory")) {
            str1 = "b201";
        } else if (cate.equals("CropGarden")) {
            str1 = "b202";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        ArticleDTO articleDTO = articleService.getArticle(artNo);
        model.addAttribute(articleDTO);

        model.addAttribute("str1", str1);

        return "/crop/talk/CropModify";
    }
}