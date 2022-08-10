package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Memo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class MemoSoloDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String contents;
    private String memberName;
    private int commentCnt;
    private int heartCnt;

    private List<Comment> commentList;



}
