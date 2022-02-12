package com.cozy.model;

import com.cozy.common.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@JsonDeserialize(builder = Reservation.Builder.class)
public class Reservation implements Serializable {
    // TODO: service request (model -> repository -> service -> controller -> register endpoint in config) - Guanxiaoxiong, Jianxun

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //enum
    private ServiceType type;
    private LocalDate datetime;
    private String state;

    //FK
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User residentId;


    public Reservation() {
    }

    private Reservation(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.datetime = builder.datetime;
        this.state = builder.state;
        this.residentId = builder.residentId;
    }

    public int getId() {
        return id;
    }

    public ServiceType getType() {
        return type;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public String getState() {
        return state;
    }

    public User getResident() {
        return residentId;
    }


    public static class Builder {
        @JsonProperty("id")
        private int id;

        @JsonProperty("type")
        private ServiceType type;

        @JsonProperty("date_time")
        private LocalDate datetime;


        @JsonProperty("state")
        private String state;

        @JsonProperty("resident_id")
        private User residentId;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setType(ServiceType type) {
            this.type = type;
            return this;
        }

        public Builder setDatetime(LocalDate datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setResident(User residentId) {
            this.residentId = residentId;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }


    }
}