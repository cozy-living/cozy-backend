package com.cozy.service;

import com.cozy.exception.CommentNotExistException;
import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.model.User;
import com.cozy.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> listByUser(int user_id) {
        return commentRepository.findByUser(new User.Builder().setId(user_id).build());
    }

    public List<Comment> listByPost(int post_id) {
        return commentRepository.findByPost(new Post.Builder().setId(post_id).build());
    }

    public void add(Comment comment){
        commentRepository.save(comment);
    }

    public void delete(int comment_id) throws CommentNotExistException {
        commentRepository.findById(comment_id).orElseThrow(() -> new CommentNotExistException("Comment is not exist."));
        commentRepository.deleteById(comment_id);
    }

}
