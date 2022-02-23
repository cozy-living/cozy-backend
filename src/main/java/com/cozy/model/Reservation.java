package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    private Date date;

//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate date;

//    @CreationTimestamp
//    @JsonProperty("date")
//    private LocalDateTime date;

    @JsonProperty("state")
    private String state;

    /*
      Many-to-one relationship to the User table.
      We can add @ignore if we do not need to access the user field in reservation.
     */
    @JoinColumn(name = "userId")
    @ManyToOne(targetEntity = User.class)
    @JsonIgnoreProperties({"role"})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}