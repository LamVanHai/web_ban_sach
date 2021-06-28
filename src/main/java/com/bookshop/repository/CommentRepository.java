package com.bookshop.repository;

import com.bookshop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
        @Query("SELECT e from Comment e WHERE e.book_comment.id=:id")
        List<Comment> findByBook_Id(@Param("id") Long id);

        @Query("select e from Comment  e where concat(e.content,e.book_comment.name,e.user_comment.name) like %?1%")
        List<Comment>  findByKeyWord(String keyWord, Pageable pageable);

        @Query("select count(e.id) from Comment  e where concat(e.content,e.book_comment.name,e.user_comment.name) like %?1%")
        int  countByKeyWord(String keyWord);
}
