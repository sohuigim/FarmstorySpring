package com.farmstory.controller.article.crop;

import com.farmstory.dto.ArticleDTO;

import com.farmstory.dto.FileDTO;
import com.farmstory.dto.pageDTO.ArticlePageRequestDTO;
import com.farmstory.dto.pageDTO.ArticlePageResponseDTO;
import com.farmstory.entity.Article;

import com.farmstory.entity.Comment;

import com.farmstory.repository.article.ArticleRepository;
import com.farmstory.service.ArticleService;
import com.farmstory.service.CommentService;
import com.farmstory.service.FileService;
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
    private final FileService fileService;
    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    @GetMapping("/crop/{cate}")
    public String cropView(@PathVariable String cate,@RequestParam(required = false) String type, @RequestParam(required = false) String keyword, Model model, ArticlePageRequestDTO articlePageRequestDTO) {
        log.info(articlePageRequestDTO);
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

        log.info("articles_controller "+articles);
        if(cate == null) {
            cate = "b201";
        }

        articlePageRequestDTO.setType(type);
        articlePageRequestDTO.setKeyword(keyword);

        ArticlePageResponseDTO articlePageResponseDTO = articleService.selectArticleAll(articlePageRequestDTO, cate);

        log.info(articlePageResponseDTO);

        model.addAttribute("pageResponseDTO", articlePageResponseDTO);


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


    @PostMapping("/crop/CropWrite")

    public String CropWrite(ArticleDTO articleDTO, String artCate) {
        log.info("article info : "+articleDTO);

        String str1 = "";
        if (artCate.equals("CropStory")) {
            str1 = "b201";
        } else if (artCate.equals("CropGarden")) {
            str1 = "b202";
        } else if (artCate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        List<FileDTO> uploadedFiles = fileService.uploadFile(articleDTO);
        articleDTO.setArtFile(uploadedFiles.size()); // 첨부 파일 갯수 초기화
        int ano = articleService.insertArticle(articleDTO);

        log.info("ano : " + ano);

        for(FileDTO fileDTO : uploadedFiles){
            fileDTO.setArtNo(ano);
            fileService.insertFile(fileDTO);
        }


        return "redirect:/crop/"+artCate;

    }

    //글보기
    @GetMapping("/crop/{cate}/CropView/{artNo}")
    public String CropView(Model model, @PathVariable("cate") String cate, @PathVariable("artNo") int artNo) {
        String str1 = "";
        if (cate.equals("CropStory")) {
            str1 = "b201";
        } else if (cate.equals("CropGarden")) {
            str1 = "b202";
        } else if (cate.equals("CropReturnfarm")) {
            str1 = "b203";
        }

        ArticleDTO articleDTO = articleService.selectArticle(artNo);
        System.out.println("article_DTO"+articleDTO.getFileList());
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
    public String CropModify(Model model, @PathVariable String cate, @PathVariable("artNo") int artNo) {
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


    //삭제
    @GetMapping("/crop/{cate}/CropDelete/{no}")
    public String CropDelete(@PathVariable String cate, @PathVariable("no") int no) {

        log.info("no : " + no);

        articleService.deleteArticle(no);

        // cate 값을 사용하여 리디렉션 경로 설정
        return "redirect:/crop/" + cate;
    }



}