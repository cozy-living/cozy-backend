package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("content")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    private Date date;

    // Many-to-one relationship to the Post table.
    @JoinColumn(name = "postId")
    @ManyToOne(targetEntity = Post.class)
    @JsonIgnoreProperties({"content", "date"})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

//    // Many-to-one relationship to the User table.
//    @JoinColumn(name = "userId")
//    @ManyToOne(targetEntity = User.class)
//    private User user;

}