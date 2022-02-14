package com.cozy.service;

import com.cozy.exception.ResourceNotFoundException;
import com.cozy.model.Event;
import com.cozy.model.User;
import com.cozy.repository.EventRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event add(int userId, Event eventRequest) {
        User user = userRepository.findById(userId);
        eventRequest.setUser(user);
        eventRepository.save(eventRequest);
        return eventRequest;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public void put(int eventId, Event eventRequest) {
        eventRepository.findById(eventId).map(event -> {
            event.setContent(eventRequest.getContent());
            eventRepository.save(event);
            return event;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("event id " + eventId + " not found"));
    }

    public ResponseEntity<?> delete(int eventId) {
        return eventRepository.findById(eventId).map(event -> {
            eventRepository.delete(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("event id " + eventId + " not found"));
    }
}