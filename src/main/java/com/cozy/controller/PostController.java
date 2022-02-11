package com.cozy.controller;

import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping(value = "/post")
    public void addComment(@RequestBody Post post){
        postService.add(post);
    }

}
