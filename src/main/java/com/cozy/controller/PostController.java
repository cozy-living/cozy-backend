package com.cozy.controller;

import com.cozy.model.Post;
import com.cozy.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {
    @RequestMapping(value = "/posts")
    public List<Post> getAllPosts() {

        User user1 = new User();

        Post post1 = new Post();

        return Arrays.asList(post1);

    }
}
