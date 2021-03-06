package com.acme.service;

import com.acme.repository.CompanyRepository;
import com.acme.repository.CreatorRepository;
import com.acme.repository.EventRepository;
import com.acme.repository.MarketplaceRepository;
import com.acme.types.Company;
import com.acme.types.Creator;
import com.acme.types.Event;
import com.acme.types.Marketplace;

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
    private CompanyRepository companyRepository;
    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MarketplaceRepository marketplaceRepository;

    public Event save(Event event) {
        if (!event.getType().equals("SUBSCRIPTION_NOTICE")) {
            Creator creator = creatorRepository.findOne(event.getCreator().getUuid());
            if (creator != null) {
                event.setCreator(creator);
            }
            if (event.getPayload().getCompany() != null) {
                Company company = companyRepository.findOne(event.getPayload().getCompany().getUuid());
                if (company != null) {
                    event.getPayload().setCompany(company);
                }
            }
        }
        Marketplace marketplace = marketplaceRepository.findOne(event.getMarketplace().getPartner());
        if (marketplace != null) {
            event.setMarketplace(marketplace);
        }
        Event e = eventRepository.saveAndFlush(event);
        return event;
    }
}
