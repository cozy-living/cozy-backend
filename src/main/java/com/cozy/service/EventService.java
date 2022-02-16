package com.cozy.service;

import com.cozy.commons.UserRole;
import com.cozy.exception.ResourceNotFoundException;
import com.cozy.exception.UserNotAuthorizedException;
import com.cozy.model.Event;
import com.cozy.model.User;
import com.cozy.repository.EventRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event add(int userId, Event eventRequest)
            throws UserNotAuthorizedException {
        User user = userRepository.findById(userId);
        if (!user.getRole().equals(UserRole.ADMIN.name())) {
            log.error("The user is not authorized to create a new event!");
            throw new UserNotAuthorizedException("The user is not authorized to create a new event!");
        }
        eventRequest.setUser(user);
        eventRequest.setDate(new Date());
        eventRepository.save(eventRequest);
        return eventRequest;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public void put(int userId, int eventId, Event eventRequest)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        User user = userRepository.getById(userId);
        // Any admin can edit an event regardless of whether they are the author or not
        if (!user.getRole().equals(UserRole.ADMIN.name())) {
            log.error("The user is not authorized to edit this event!");
            throw new UserNotAuthorizedException("The user is not authorized to edit this event!");
        }
        eventRepository.findById(eventId).map(e -> {
            e.setTitle(eventRequest.getTitle());
            e.setContent(eventRequest.getContent());
            e.setDate(new Date());
            eventRepository.save(e);
            return e;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("event id " + eventId + " not found"));
    }

    public ResponseEntity<?> delete(int userId, int eventId)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        User user = userRepository.getById(userId);
        // Any admin can delete an event regardless of whether they are the author or not
        if (!user.getRole().equals(UserRole.ADMIN.name())) {
            log.error("The user is not authorized to delete this event!");
            throw new UserNotAuthorizedException("The user is not authorized to delete this event!");
        }
        return eventRepository.findById(eventId).map(e -> {
            eventRepository.delete(e);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("event id " + eventId + " not found"));
    }
}