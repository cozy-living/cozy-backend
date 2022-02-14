package com.cozy.repository;

import com.cozy.model.Post;
import com.cozy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUserId(int userId);

//    Post findById(int postId);

}