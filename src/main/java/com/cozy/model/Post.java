package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post implements Serializable {
    // TODO: post (model -> repository -> service -> controller -> register endpoint in config) - Zhiteng
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postdate;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user_id;

    @OneToMany
    @JoinColumn(name = "comment")
    private Comment comments;

    public Post() {}

    private Post(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.postdate = builder.postdate;
        this.user_id = builder.user_id;
        this.comments = builder.comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    public static class Builder {
        @JsonProperty("id")
        private int id;

        @JsonProperty("content")
        private String content;

        @JsonProperty("postdate")
        private Date postdate;

        @JsonProperty("user_id")
        private User user_id;

        @JsonProperty("comments")
        private Comment comments;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setPostdate(Date postdate) {
            this.postdate = postdate;
            return this;
        }

        public Builder setUser_id(User user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder setComments(Comment comments) {
            this.comments = comments;
            return this;
        }
    }


}
