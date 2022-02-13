package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    /* @JsonIgnore will ignore everything, but we just want to
       serialize the setter and ignore the getter so that
       the raw password will hide in http response. So we will
       NOT be using @JsonIgnore on the password field.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty("suite")
    private String suite;

    @JsonProperty("role")
    private String role;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
//            mappedBy = "user", targetEntity = Reservation.class)
//    @JsonProperty("reservations")
//    private List<Reservation> reservations;

}

