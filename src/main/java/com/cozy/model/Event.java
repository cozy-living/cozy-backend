package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// TODO: dashboard (model -> repository -> service -> controller -> register endpoint in config) - Amber

//@Entity
//@Table(name = "event")
//@JsonDeserialize(builder = Event.Builder.class)
public class Event implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//    private String content;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date date;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private User admin;
//
//    public Event() {}
//
//    private Event(Builder builder) {
//        this.id = builder.id;
//        this.content = builder.content;
//        this.date = builder.date;
//        this.admin = builder.admin;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public Event setContent(String content) {
//        this.content = content;
//        return this;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public User getAdmin() {
//        return admin;
//    }
//
//    public static class Builder {
//        @JsonProperty("id")
//        private int id;
//
//        @JsonProperty("content")
//        private String content;
//
//        @JsonProperty("date")
//        private Date date;
//
//        @JsonProperty("admin")
//        private User admin;
//
//        public Builder setId(int id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder setContent(String content) {
//            this.content = content;
//            return this;
//        }
//
//        public Builder setDate(Date date) {
//            this.date = date;
//            return this;
//        }
//
//        public Builder setAdmin(User admin) {
//            this.admin = admin;
//            return this;
//        }
//
//        public Event build() {
//            return new Event(this);
//        }
//    }
}
