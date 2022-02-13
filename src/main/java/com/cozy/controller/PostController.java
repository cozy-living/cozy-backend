package com.cozy.controller;

import com.cozy.model.Reservation;
import com.cozy.service.PostService;
import com.cozy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

//    private PostService postService;
//
//    @Autowired
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//
//    @PostMapping("/{userId}/posts")
//    public Reservation addReservation(@PathVariable(value = "userId") int userId,
//                                      @RequestBody Reservation reservation) {
//        return postService.add(userId, post);
//    }
}

