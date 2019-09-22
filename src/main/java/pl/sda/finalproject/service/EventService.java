package pl.sda.finalproject.service;

import org.springframework.stereotype.Service;
import pl.sda.finalproject.events.Event;
import pl.sda.finalproject.events.EventForm;
import pl.sda.finalproject.events.EventRepository;
import pl.sda.finalproject.exception.UserNotFoundException;
import pl.sda.finalproject.user.User;
import pl.sda.finalproject.user.UserContextService;
import pl.sda.finalproject.user.UserRepository;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserContextService userContextService;
    private final UserRepository userRepository;


    public EventService(EventRepository eventRepository, UserContextService userContextService, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userContextService = userContextService;
        this.userRepository = userRepository;
    }

    public void saveEvent(EventForm eventForm) {

// optional moze byc pusty ale nie moze byc nullem
        Optional<User> byUsername = userRepository.findByUsername(userContextService.getLoggedUsername());
        if (byUsername.isPresent()) {
            Event newEvent = new Event();
            newEvent.setTitle(eventForm.getTitle());
            newEvent.setDescription(eventForm.getDescription());
            newEvent.setStartingDate(eventForm.getStartingDate());
            newEvent.setEndingDate(eventForm.getEndingDate());

            newEvent.setUser(byUsername.get());
            eventRepository.save(newEvent);
        } else {

            throw new UserNotFoundException();
        }
    }
}