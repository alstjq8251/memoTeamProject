package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemoMainResponseDto {
    private Long id;
    private String title;
    private String memberName;
    private Long heartCnt;
    private Long commentCnt;



    public MemoMainResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.memberName = memo.getMemberName();
        this.heartCnt = Long.valueOf(memo.getHeartList().size());
        this.commentCnt = Long.valueOf(memo.getCommentList().size());
    }
}
