package com.cozy.repository;

import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByUser(User user);
    List<Comment> findByPost(Post post);
}
