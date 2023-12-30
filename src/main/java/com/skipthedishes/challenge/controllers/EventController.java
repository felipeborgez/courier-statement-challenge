package com.skipthedishes.challenge.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.challenge.api.events.AdjustmentModified;
import com.skipthedishes.challenge.api.events.BonusModified;
import com.skipthedishes.challenge.api.events.CourierEvent;
import com.skipthedishes.challenge.api.events.DeliveryCreated;
import com.skipthedishes.challenge.business.old_entities.RawEvent;
import com.skipthedishes.challenge.business.repositories.EventRepository;
import com.skipthedishes.challenge.business.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/event")
public class EventController {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Autowired
    EventService service;

    private static final Logger log = Logger.getLogger(EventController.class.getName());

    @PostMapping("/delivery")
    @ResponseStatus( HttpStatus.CREATED )
    public String delivery(@RequestBody String payload){
        UUID correlationID = UUID.randomUUID();
        service.create(correlationID, payload);
        try {
            CourierEvent event = objectMapper.readValue(payload, DeliveryCreated.class);
            service.process(event);
            service.complete(correlationID, event.convertAsTransaction().transactionReferenceId());
            return payload;
        } catch (JsonProcessingException exception){
            service.error(correlationID);
            return "Json bad formatted";
        } catch (Exception exception){
            service.error(correlationID);
            return "Something unexpected happened";
        }
    }

    @PostMapping("/adjustment")
    @ResponseStatus( HttpStatus.CREATED )
    public String adjustment(@RequestBody String payload) {
        UUID correlationID = UUID.randomUUID();
        service.create(correlationID, payload);
        try {
            CourierEvent event = objectMapper.readValue(payload, AdjustmentModified.class);
            service.process(event);
            service.complete(correlationID, event.convertAsTransaction().transactionReferenceId());
            return payload;
        } catch (JsonProcessingException exception){
            service.error(correlationID);
            return "Json bad formatted";
        } catch (Exception exception){
            service.error(correlationID);
            return "Something unexpected happened";
        }
    }

    @PostMapping("/bonus")
    @ResponseStatus( HttpStatus.CREATED )
    public String bonus(@RequestBody String payload) {
        UUID correlationID = UUID.randomUUID();
        service.create(correlationID, payload);
        try {
            CourierEvent event = objectMapper.readValue(payload, BonusModified.class);
            service.process(event);
            service.complete(correlationID, event.convertAsTransaction().transactionReferenceId());
            return payload;
        } catch (JsonProcessingException exception){
            service.error(correlationID);
            return "Json bad formatted";
        } catch (Exception exception){
            service.error(correlationID);
            return "Something unexpected happened";
        }
    }

}
