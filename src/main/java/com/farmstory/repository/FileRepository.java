package com.farmstory.repository;

import com.farmstory.entity.Article;
import com.farmstory.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    public List<File> findAllByArticle(Article article);
}
