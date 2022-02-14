package com.cozy.service;

import com.cozy.exception.ResourceNotFoundException;
import com.cozy.model.Post;
import com.cozy.model.User;
import com.cozy.repository.PostRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post add(int userId, Post postRequest) {
        User user = userRepository.findById(userId);
        postRequest.setUser(user);
        postRepository.save(postRequest);
        return postRequest;
    }

    public List<Post> getAllByUser(int userId) {
        return postRepository.findAllByUserId(userId);
    }

    public void put(int postId, Post postRequest) {
        postRepository.findById(postId).map(post -> {
            post.setContent(postRequest.getContent());
            postRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("post id " + postId + " not found"));
    }

    public ResponseEntity<?> delete(int postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("post id " + postId + " not found"));
    }
}
