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
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    private Date date;

    @JsonProperty("fileUrl")
    private String fileUrl;

    // Many-to-one relationship to the User table.
    @JoinColumn(name = "userId")
    @ManyToOne(targetEntity = User.class)
    @JsonIgnoreProperties({"role"})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}