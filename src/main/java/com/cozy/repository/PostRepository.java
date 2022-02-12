package com.cozy.repository;

import com.cozy.model.Comment;
import com.cozy.model.Post;
import com.cozy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByPost(int id, String content, Date postdate, User user_id, Comment comments);
}
