package com.sparta.memoproject.service;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemberRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final MemoService memoService;
    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;

    //게시글 좋아요
    @Transactional
    public Long addHeartToMemo(Long memoId) {
        String nickname = memoService.getNickname();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!heartRepository.findByMemoAndNickname(memo, nickname).isEmpty()) {
            heartRepository.delete(heartRepository.findByNicknameAndMemo(nickname, memo));
        } else {
            Heart heart = new Heart(nickname, memo);
            heartRepository.save(heart);
        }
        return heartRepository.countByMemo(memo);
    }
    //댓글 좋아요
    @Transactional
    public Long addHeartToComment(Long commentId) {
        String nickname = memoService.getNickname();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 존재하지 않습니다."));

        if (!heartRepository.findByCommentAndNickname(comment, nickname).isEmpty()) {
            heartRepository.delete(heartRepository.findByNicknameAndComment(nickname, comment));
        } else{
            Heart heart = new Heart(nickname, comment);
            heartRepository.save(heart);
        }
        return heartRepository.countByComment(comment);
    }

//    public Long addHeartToComment(Long memoId, Long commentId) {
//
//    }
}