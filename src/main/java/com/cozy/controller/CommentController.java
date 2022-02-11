package com.cozy.controller;

import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping(value = "/comment/{postId}")
    public List<Comment> listComment(@PathVariable int postId){
        return commentService.listByPost(postId);
    }

    @PostMapping(value = "/comment")
    public void addComment(@RequestBody Comment comment){
        commentService.add(comment);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        commentService.delete(commentId);
    }

}
