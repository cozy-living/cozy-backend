package com.cozy.service;

import com.cozy.model.Post;
import com.cozy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private com.cozy.repository.PostRepository PostRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.PostRepository = postRepository;
    }

    public void addPost(Post post) {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        return;
    }
}
