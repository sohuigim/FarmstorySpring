package com.farmstory.repository;

import com.farmstory.entity.Article;
import com.farmstory.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByArticleArtNo(int artNo);

}
