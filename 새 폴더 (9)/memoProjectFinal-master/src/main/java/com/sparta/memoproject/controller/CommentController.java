package com.sparta.memoproject.controller;

import com.sparta.memoproject.dto.CommentDto;
import com.sparta.memoproject.dto.CommentRequestDto;
import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/comment")
public class CommentController {

    private final CommentService commentService;  // 필수적인 요소이기 때문에 final 선언
    private final CommentRepository commentRepository;

    @Secured("ROLE_USER")
    @PostMapping("/{id}")
    public Comment addComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(id, commentRequestDto);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}/{commentId}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentId, commentRequestDto);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{id}/{commentId}")
    public Boolean deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        return commentService.deleteComment(id, commentId);
    }

    @PostMapping("{id}/{commentId}")
    public Comment addChildComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.addChildComment(id, commentId, requestDto);
    }

    @GetMapping("/comment")
    public List<Comment> readComment() {
        return commentRepository.findAllByParent_IdIsNullOrderByModifiedAtDesc();
    }//parent_id

    @GetMapping("/comment/{Id}")
    public CommentDto readCommentHeart(@PathVariable Long Id) {
        return commentService.readCommentHeart(Id);
    }

    @GetMapping("/comment/child/{Id}")
    public CommentDto readChildCommentHeart(@PathVariable Long Id){
        return commentService.readChildCommentHeart(Id);
    }

}


//    @GetMapping("/api/memos")
//    public List<Memo> readMemo(){
//        return memoRepository.findAllByOrderByModifiedAtDesc();
//    }
//}
