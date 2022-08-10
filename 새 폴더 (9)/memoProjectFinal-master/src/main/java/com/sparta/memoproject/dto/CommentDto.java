package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Comment;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;

    private String content;

    private String memberName;

    private Long heartcnt;

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberName = comment.getMemberName();
        this.heartcnt = Long.valueOf(comment.getHeartList().size());
    }
}