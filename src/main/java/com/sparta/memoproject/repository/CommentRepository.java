package com.sparta.memoproject.repository;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countByMemo(Memo memo);

    List<Comment> findAllByMemo(Memo memo);
}