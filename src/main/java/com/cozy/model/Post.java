package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "post")
@JsonDeserialize(builder = Post.Builder.class)
public class Post {

    // TODO: post (model -> repository -> service -> controller -> register endpoint in config) - Zhiteng

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    public Post(){
    }

    public Post(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static class Builder {
        @JsonProperty("id")
        private int id;

        @JsonProperty("content")
        private String content;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Post build(){
            return new Post(this);
        }
    }
}
