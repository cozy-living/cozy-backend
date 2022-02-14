package com.cozy.controller;

import com.cozy.model.Post;
import com.cozy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * addPost: Resident creates a new post in the discuss board
     * Method: POST
     * Endpoint: /{userId}/posts
     */
    @PostMapping("/{userId}/posts")
    public Post addPost(@PathVariable(value = "userId") int userId,
                                      @RequestBody Post post) {
        return postService.add(userId, post);
    }

    /**
     * listPostByUser: Resident lists all of his or her past posts
     * Method: GET
     * Endpoint: /{userId}/posts
     */
    @GetMapping("/{userId}/posts")
    public List<Post> listPostsByUser(
            @PathVariable (value = "userId") int userId) {
        return postService.getAllByUser(userId);
    }

    /**
     * listPosts: List all posts published
     * Method: GET
     * Endpoint: /posts
     */
    @GetMapping("/posts")
    public List<Post> listPosts() {
        return postService.getAll();
    }

    /**
     * editPost: The post owner edits his or her post
     * Method: PUT
     * Endpoint: /{userId}/posts/{postId}
     */
    @PutMapping("/{userId}/posts/{postId}")
    public void editPost(@PathVariable("userId") int userId, @PathVariable("postId") int postId,
                                @RequestBody Post postRequest) {
        postService.put(userId, postId, postRequest);
    }

    /**
     * deletePost: The post owner deletes his or her post
     * Method: DELETE
     * Endpoint: /{userId}/posts/{postId}
     */
    @DeleteMapping("/{userId}/posts/{postId}")
    public void deletePost(@PathVariable("userId") int userId,
                           @PathVariable("postId") int postId) {
        postService.delete(userId, postId);
    }
}

