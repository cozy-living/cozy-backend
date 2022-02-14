package com.cozy.controller;

import com.cozy.model.Comment;
import com.cozy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * addComment: User adds a new comment under a post
     * Method: POST
     * Endpoint: /{postId}/comments
     */
    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable(value = "postId") int postId,
                              @RequestBody Comment comment) {
        return commentService.add(postId, comment);
    }

    /**
     * listComments: List all comments under a post
     * Method: GET
     * Endpoint: /{postId}/comments
     */
    @GetMapping("/{postId}/comments")
    public List<Comment> listComments(@PathVariable(value = "postId") int postId) {
        return commentService.getAllByPost(postId);
    }
}