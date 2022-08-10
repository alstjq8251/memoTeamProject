package com.sparta.memoproject.service;


import com.sparta.memoproject.dto.*;
import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final MemoService memoService;

    @Transactional
    public MyPageDto readPage() {
        String nickname = memoService.getNickname();
        List<Memo> memoList = memoRepository.findAllByMemberName(nickname);
        List<Comment> commentList = commentRepository.findAllByMemberNameAndParent_IdIsNull(nickname);
        List<Comment> childcommentList = commentRepository.findAllByMemberNameAndParent_IdIsNotNull(nickname);
        List<Heart> heartcheckmemoList = heartRepository.findAllByNicknameAndMemo_IdIsNotNull(nickname);
        List<Heart> heartcheckcommentList = heartRepository.findAllByNicknameAndMemo_IdIsNull(nickname);
        List<Comment> heartcommentList = new ArrayList<>();
        List<Comment> heartchildcommentList = new ArrayList<>();
        for(int i=0; i<heartcheckcommentList.size(); i++){
            if(heartcheckcommentList.get(i).getComment().getParent() == null)
                heartcommentList.add(heartcheckcommentList.get(i).getComment()); // 댓글
            else
                heartchildcommentList.add(heartcheckcommentList.get(i).getComment()); // 대댓글
        }

        MyPageDto myPageDto = new MyPageDto();
        for(Memo memo : memoList){
            MemoTfDto memoTfDto = MemoTfDto.builder()
                .createdAt(memo.getCreatedAt())
                .modifiedAt(memo.getModifiedAt())
                .id(memo.getId())
                .title(memo.getTitle())
                .contents(memo.getContents())
                .memberName(memo.getMemberName())
                .commentCnt(memo.getCommentList().size())
                .heartCnt(memo.getHeartList().size())
                .urlPath(memo.getUrlPath())
                .build();
            myPageDto.addMomoTfDto(memoTfDto);
        }
        for(Comment comment : commentList){
            CommentTfDto commentTfDto = CommentTfDto.builder()
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .id(comment.getId())
                .content(comment.getContent())
                .memberName(comment.getMemberName())
                .heartcnt(Long.valueOf(comment.getHeartList().size()))
                .build();
            myPageDto.addCommentTfDto(commentTfDto);
        }
        for(Comment childComment : childcommentList){
            CommentTfDto commentTfDto = CommentTfDto.builder()
                .createdAt(childComment.getCreatedAt())
                .modifiedAt(childComment.getModifiedAt())
                .id(childComment.getId())
                .content(childComment.getContent())
                .memberName(childComment.getMemberName())
                .heartcnt(Long.valueOf(childComment.getHeartList().size()))
                .build();
            myPageDto.addChildCommentTfDto(commentTfDto);
        }
        for(Heart heartmemo : heartcheckmemoList){
            MemoTfDto memoTfDto = MemoTfDto.builder()
                .createdAt(heartmemo.getMemo().getCreatedAt())
                .modifiedAt(heartmemo.getMemo().getModifiedAt())
                .id(heartmemo.getMemo().getId())
                .title(heartmemo.getMemo().getTitle())
                .contents(heartmemo.getMemo().getContents())
                .memberName(heartmemo.getMemo().getMemberName())
                .commentCnt(heartmemo.getMemo().getCommentList().size())
                .heartCnt(heartmemo.getMemo().getHeartList().size())
                .urlPath(heartmemo.getMemo().getUrlPath())
                .build();
            myPageDto.addHeartmemoTfDto(memoTfDto);
        }
        for(Comment heartcomment : heartcommentList){
            CommentTfDto commentTfDto = CommentTfDto.builder()
                .createdAt(heartcomment.getCreatedAt())
                .modifiedAt(heartcomment.getModifiedAt())
                .id(heartcomment.getId())
                .content(heartcomment.getContent())
                .memberName(heartcomment.getMemberName())
                .heartcnt(Long.valueOf(heartcomment.getHeartList().size()))
                .build();
            myPageDto.addHeartcommentTfDto(commentTfDto);
        }
        for(Comment heartchildComment : heartchildcommentList){
            CommentTfDto commentTfDto = CommentTfDto.builder()
                .createdAt(heartchildComment.getCreatedAt())
                .modifiedAt(heartchildComment.getModifiedAt())
                .id(heartchildComment.getId())
                .content(heartchildComment.getContent())
                .memberName(heartchildComment.getMemberName())
                .heartcnt(Long.valueOf(heartchildComment.getHeartList().size()))
                .build();
            myPageDto.addHeartchildCommentTfDto(commentTfDto);
        }

        return myPageDto;
    }
}
