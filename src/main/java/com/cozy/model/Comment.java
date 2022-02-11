package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@JsonDeserialize(builder = Comment.Builder.class)
public class Comment implements Serializable {

    // TODO: comment (model -> repository -> service -> controller -> register endpoint in config) - Jiayi
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @JsonProperty("comment_date")
    private LocalDate commentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    private Comment(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.commentDate = builder.commentDate;
        this.user = builder.user;
        this.post = builder.post;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getComment_date() {
        return commentDate;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public static class Builder {
        @JsonProperty("id")
        private int id;

        @JsonProperty("content")
        private String content;

        @JsonProperty("comment_date")
        private LocalDate commentDate;

        @JsonProperty("user")
        private User user;

        @JsonProperty("post")
        private Post post;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setComment_date(LocalDate commentDate) {
            this.commentDate = commentDate;
            return this;
        }

        public Builder setUser(User resident) {
            this.user = resident;
            return this;
        }

        public Builder setPost(Post post) {
            this.post = post;
            return this;
        }

        public Comment build(){
            return new Comment(this);
        }
    }
}