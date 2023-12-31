package com.skipthedishes.challenge.business.services;

import com.skipthedishes.challenge.business.old_entities.Delivery;
import com.skipthedishes.challenge.business.old_entities.Transaction;
import com.skipthedishes.challenge.business.repositories.TransactionRepository;
//import com.skipthedishes.challenge.messaging.publisher.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DeliveryService {

    private static final Logger LOGGER = Logger.getLogger(DeliveryService.class.getName());

    @Autowired
    TransactionRepository repository;

//    @Autowired
//    SQSEventPublisher publisher;
//    EventPublisher publisher;

//    @Autowired
//    KafkaMessagePublisher kafkaPublisher;


    public void insert(Transaction transaction){
        repository.save(transaction);
    }


    public void publish(Delivery delivery) {
        LOGGER.info(delivery.toString());
//        publisher.send(delivery);
    }
}
