package com.acme.service;

import com.acme.repository.CreatorRepository;
import com.acme.repository.EventRepository;
import com.acme.types.Creator;
import com.acme.types.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bdraraujo on 16-04-18.
 */
@Service
@Transactional
public class EventService {
    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event) {
        Creator creator = creatorRepository.findOne(event.getCreator().getUuid());
        if (creator != null) {
            event.setCreator(creator);
        }
        Event e = eventRepository.saveAndFlush(event);
        return event;
    }
}
