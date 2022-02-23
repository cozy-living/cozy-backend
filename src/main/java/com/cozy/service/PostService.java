package com.cozy.service;

import com.cozy.exception.ResourceNotFoundException;
import com.cozy.exception.UserNotAuthorizedException;
import com.cozy.model.Post;
import com.cozy.model.User;
import com.cozy.repository.CommentRepository;
import com.cozy.repository.PostRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private AmazonClient amazonClient;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository,
                       CommentRepository commentRepository, AmazonClient amazonClient) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.amazonClient = amazonClient;
    }

    public Post add(int userId, Post postRequest, MultipartFile file) {
        User user = userRepository.findById(userId);
        postRequest.setUser(user);
        postRequest.setDate(new Date());
        String fileUrl = amazonClient.uploadFile(file);
        postRequest.setFileUrl(fileUrl);
        postRepository.save(postRequest);
        return postRequest;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllByUser(int userId) {
        return postRepository.findAllByUserId(userId);
    }

    public void put(int userId, int postId, Post postRequest)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        // Only the author can edit the post
        if (userId != postRepository.getById(postId).getUser().getId()) {
            log.error("The user is not authorized to edit this post!");
            throw new UserNotAuthorizedException("The user is not authorized to edit this post!");
        }
        postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setDate(new Date());
            postRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("post id " + postId + " not found"));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<?> delete(int userId, int postId)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        // Only the author can delete the post
        if (userId != postRepository.getById(postId).getUser().getId()) {
            log.error("The user is not authorized to delete this post!");
            throw new UserNotAuthorizedException("The user is not authorized to delete this post!");
        }
        commentRepository.deleteAllByPostId(postId);
        return postRepository.findById(postId).map(post -> {
            amazonClient.deleteFileFromS3Bucket(post.getFileUrl());
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("post id " + postId + " not found"));
    }
}
