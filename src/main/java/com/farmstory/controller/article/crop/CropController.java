package com.farmstory.controller.article.crop;

import com.farmstory.dto.ArticleDTO;


import com.farmstory.dto.pageDTO.ArticlePageResponseDTO;
import com.farmstory.dto.pageDTO.PageRequestDTO;
import com.farmstory.dto.pageDTO.PageResponseDTO;
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
    private final CommentService commentService;

    @GetMapping("/crop/{cate}")
    public String cropStory(@PathVariable String cate, Model model, PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
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

        if(cate == null) {
            cate = "0";
        }

        PageResponseDTO pageResponseDTO = articleService.selectProductAll(pageRequestDTO, cate);
        model.addAttribute("articlePageResponseDTO", pageResponseDTO);
        System.out.println(articles);

        return "/crop/" + cate;
    }

    //글쓰기(가져오기)
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

        System.out.println("comments :" + comments);
        System.out.println(model);
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

        // 카테고리와 관련된 추가 정보 처리
        model.addAttribute("cate", cate);

        return "/crop/talk/CropModify";
    }

    // 게시물 수정을 처리하는 POST 요청
    @PostMapping("/crop/{cate}/CropModify/{artNo}")
    public String updateArticle(ArticleDTO articleDTO,
                                @PathVariable String cate,
                                @PathVariable("artNo") int artNo,
                                Model model) {
        String str1 = "";
        if (cate.equals("CropStory")) {
            str1 = "b101";
        } else if (cate.equals("CropGarden")) {
            str1 = "b102";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b103";
        }

        // 게시물 정보 업데이트
        articleDTO.setArtNo(artNo); // 게시물 번호 설정
        articleService.updateArticle(articleDTO); // DB 업데이트

        model.addAttribute("str1", str1);

        // 수정 완료 후 게시글 상세 페이지로 리다이렉트
        return "redirect:/crop/" + articleDTO.getArtCate() + "/CropView/" + articleDTO.getArtNo();


        //삭제
//    @GetMapping("/crop/{cate}/deleteArticle/{artNo}")
//    public String CropStoryDelete(int artNo){
//
//        articleService.deleteArticle(artNo);
//
//        return "/crop/talk/CropDelete";
//    }

    }

}
