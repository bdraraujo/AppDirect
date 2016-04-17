package com.acme.repository;

import com.acme.types.Event;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdraraujo on 16-04-16.
 */
public abstract class EventRepository implements JpaRepository<Event, Long> {
}
