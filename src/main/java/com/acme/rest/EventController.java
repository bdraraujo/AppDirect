package com.acme.rest;

import com.acme.repository.EventRepository;
import com.acme.types.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bdraraujo on 16-04-17.
 */
@RestController
public class EventController {
    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/events")
    @ResponseBody
    public List<Event> eventListing() {
        return eventRepository.findAll();
    }
}
