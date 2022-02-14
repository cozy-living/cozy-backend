package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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

    @JsonProperty("content")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    private Date date;

    // Many-to-one relationship to the User table.
    @JoinColumn(name = "userId")
    @ManyToOne(targetEntity = User.class)
    private User user;

}