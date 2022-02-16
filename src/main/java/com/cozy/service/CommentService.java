package com.cozy.service;

import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.repository.CommentRepository;
import com.cozy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Comment add(int postId, Comment commentRequest) {
        Post post = postRepository.getById(postId);
        commentRequest.setPost(post);
        commentRequest.setDate(new Date());
        commentRepository.save(commentRequest);
        return commentRequest;
    }

    public List<Comment> getAllByPost(int postId) {
        return commentRepository.findAllByPostId(postId);
    }
}