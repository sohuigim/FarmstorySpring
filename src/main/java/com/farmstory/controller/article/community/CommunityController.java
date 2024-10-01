package com.farmstory.controller.article.community;

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
public class CommunityController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final CommentService commentService;

//    public CommunityController(ArticleService articleService) {
//        this.articleService = articleService;
//    }

    @GetMapping("/community/{cate}")
    public String CommunityNoti(@PathVariable String cate, Model model) {
        String str1 = "";
        if (cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        model.addAttribute("str1", str1);

//        System.out.println("cate : "+ cate);
//        System.out.println("cate : "+ str1);
        List<Article> articles = articleService.selectArticles(cate);
        model.addAttribute("articles", articles);
        System.out.println(articles);

        return "/community/" + cate;
    }

    //글쓰기
    @GetMapping("/community/{cate}/CommunityWrite/")
    public String CommunityWrite(Model model, @PathVariable("cate") String cate) {

        String str1 = "";
        if (cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (cate.equals("CommunityFaq")) {
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

    public String CommunityWrite(Model model, @ModelAttribute ArticleDTO articleDTO, String artCate) {

        String str1 = "";
        if (artCate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (artCate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (artCate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (artCate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (artCate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        log.info("Received artCate: " + artCate);
        System.out.println("ArticleDTO: " + articleDTO);
        System.out.println("Received artCate: " + artCate);  // 받아온 artCate 확인

        // DB에 저장
        articleService.saveArticle(articleDTO);

        System.out.println("글이 성공적으로 저장되었습니다.");

        // db제출
        model.addAttribute("str1", "b101");
//        return "redirect:/community/CommunityNotice";
        return "redirect:/community/" + artCate;
    }

    //글보기
    @GetMapping("/community/{cate}/CommunityView/{artNo}")
    public String CommunityView(Model model, @PathVariable("cate") String cate, @PathVariable("artNo") int artNo) {
        String str1 = "";
        if (cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        ArticleDTO articleDTO = articleService.getArticle(artNo);
        List<Comment> comments = commentService.selectCommentByArtNo(articleDTO.getArtNo());
        model.addAttribute(articleDTO);

        model.addAttribute("str1", str1);
        model.addAttribute("comments", comments);
//        System.out.println("cate : "+ cate);

        System.out.println(str1);
        System.out.println(cate);
        return "/community/talk/CommunityView";
    }

    // 글수정
    @GetMapping("/community/{cate}/CommunityModify/{artNo}")
    public String CommunityViewModify(Model model, @PathVariable String cate, @PathVariable("artNo") int artNo) {
        String str1 = "";
        System.out.println(cate);
        if (cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        // 게시물 정보 가져오기
        ArticleDTO articleDTO = articleService.getArticle(artNo);
        model.addAttribute(articleDTO);
        model.addAttribute("str1", str1);

        // 카테고리와 관련된 추가 정보 처리
        model.addAttribute("cate", cate);

        // 수정 폼으로 이동
        return "community/talk/CommunityModify";
    }

    // 게시물 수정을 처리하는 POST 요청
    @PostMapping("/community/{cate}/CommunityModify/{artNo}")
    public String updateArticle(ArticleDTO articleDTO,
                                @PathVariable String cate,
                                @PathVariable("artNo") int artNo,
                                Model model) {
        String str1 = "";
        if (cate.equals("CommunityNotice")) {
            str1 = "b101";
        } else if (cate.equals("CommunityDiet")) {
            str1 = "b102";
        } else if (cate.equals("CommunityChef")) {
            str1 = "b103";
        } else if (cate.equals("CommunityCs")) {
            str1 = "b104";
        } else if (cate.equals("CommunityFaq")) {
            str1 = "b105";
        }

        // 게시물 정보 업데이트
        articleDTO.setArtNo(artNo); // 게시물 번호 설정
        articleService.updateArticle(articleDTO); // DB 업데이트

        model.addAttribute("str1", str1);

        // 수정 완료 후 게시글 상세 페이지로 리다이렉트
        return "redirect:/community/" + articleDTO.getArtCate() + "/CommunityView/" + articleDTO.getArtNo();


// 삭제기능 추가 구현 예정
        // 게시물 삭제 요청 처리
//        @PostMapping("/community/{cate}/deleteArticle/{artNo}")
//        public String deleteArticle (@PathVariable("cate") String cate,
//        @PathVariable("artNo") int artNo,
//        Model model){
//            // 카테고리 코드 결정
//            String str1 = determineCategoryCode(cate);
//
//            // 게시물 삭제
//            articleService.deleteArticle(artNo);
//
//            // 카테고리 코드와 함께 해당 카테고리로 리다이렉트
//            model.addAttribute("str1", str1);
//            return "redirect:/community/" + cate;
//        }
    }
}

