package com.skipthedishes.challenge.business.services;

import com.skipthedishes.challenge.api.events.DeliveryCreated;
import com.skipthedishes.challenge.api.events.CourierEvent;
import com.skipthedishes.challenge.business.entities.RawEvent;
import com.skipthedishes.challenge.business.repositories.DeliveryRepository;
import com.skipthedishes.challenge.business.repositories.EventRepository;
import com.skipthedishes.challenge.business.repositories.TransactionRepository;
//import com.skipthedishes.challenge.messaging.publisher.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
//    KafkaMessagePublisher kafkaPublisher;

    @Value("${messaging.queue.delivery_created}")
    private String deliveryCreatedEventTopic;
    @Value("${messaging.queue.adjustment_modified}")
    private String adjustmentModifiedEventTopic;
    @Value("${messaging.queue.bonus_modified}")
    private String bonusModifiedEventTopic;

    @Autowired
    CourierEventValidator validator;

    @Autowired
    EventRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    public RawEvent create(UUID id, String payload){
        RawEvent raw = new RawEvent(id, null, payload, RawEvent.Status.PENDING, Instant.now());
        repository.save(raw);
        return raw;
    }

    public int complete(UUID id, UUID reference_id){
        return repository.update(id, reference_id, RawEvent.Status.PROCESSED);
    }

    public int error(UUID id){
        return repository.update(id, null, RawEvent.Status.ERROR);
    }

    public void process(CourierEvent event){
        if (validator.validate(event)){
            if (event instanceof DeliveryCreated){
                deliveryRepository.save(
                    ((DeliveryCreated) event).convertAsDelivery()
                );
            }
            transactionRepository.save(
                event.convertAsTransaction()
            );
        }
    }



    public void publish(DeliveryCreated event){
//        kafkaPublisher.sendEventsToTopic(deliveryCreatedEventTopic, event);
//            kafkaPublisher.sendMessageToTopic(deliveryCreatedEventTopic, objectMapper.writeValueAsString(event);
    }
}
