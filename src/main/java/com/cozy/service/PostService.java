package com.cozy.service;

import com.cozy.model.Post;
import com.cozy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepositoryo){
        this.postRepository = postRepositoryo;
    }

    public void add(Post post){
        postRepository.save(post);
    }

}
