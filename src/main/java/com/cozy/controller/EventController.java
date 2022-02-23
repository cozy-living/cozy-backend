package com.cozy.controller;

import com.cozy.model.Event;
import com.cozy.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * addEvent: Admin creates a new event in the main dashboard page
     * Method: POST
     * Endpoint: /{userId}/events
     */
    @PostMapping("/{userId}/events")
    public Event addEvent(@PathVariable(value = "userId") int userId,
                          @ModelAttribute Event event,
                          @RequestPart(value = "file") MultipartFile file) {
        return eventService.add(userId, event, file);
    }

    /**
     * listEvents: List all events published
     * Method: GET
     * Endpoint: /events
     */
    @GetMapping("/events")
    public List<Event> listEvents() {
        return eventService.getAll();
    }

    /**
     * editEvent: Admin edits a published event
     * Method: PUT
     * Endpoint: /{userId}/events/{eventId}
     */
    @PutMapping("/{userId}/events/{eventId}")
    public void editEvent(@PathVariable("userId") int userId, @PathVariable("eventId") int eventId,
                         @RequestBody Event eventRequest) {
        eventService.put(userId, eventId, eventRequest);
    }

    /**
     * deleteEvent: Admin deletes a published event
     * Method: DELETE
     * Endpoint: /{userId}/events/{eventId}
     */
    @DeleteMapping("/{userId}/events/{eventId}")
    public void deleteEvent(@PathVariable("userId") int userId,
                            @PathVariable("eventId") int eventId) {
        eventService.delete(userId, eventId);
    }
}


