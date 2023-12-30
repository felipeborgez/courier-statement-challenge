package com.skipthedishes.challenge.business.services;

import com.skipthedishes.challenge.api.events.AdjustmentModified;
import com.skipthedishes.challenge.api.events.BonusModified;
import com.skipthedishes.challenge.api.events.CourierEvent;
import com.skipthedishes.challenge.api.events.DeliveryCreated;
import com.skipthedishes.challenge.business.old_entities.Transaction;
import com.skipthedishes.challenge.business.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CourierEventValidator {

    private static final Logger log = Logger.getLogger(CourierEventValidator.class.getName());

    @Autowired
    private TransactionRepository repository;

    public boolean validate(CourierEvent event) {
        // refactor with polymorphism
        if (event instanceof DeliveryCreated){
            return validate((DeliveryCreated) event);
        }

        if (event instanceof AdjustmentModified){
            return validate((AdjustmentModified) event);
        }

        if (event instanceof BonusModified){
            return validate((BonusModified) event);
        }

        return false;
    }

    private boolean deliveryIsAlreadyCreated(UUID id) {
        return repository.findAllByDeliveryId(id)
                .stream()
                .anyMatch(t -> t.type() == Transaction.Type.CREATION);
    }

    private boolean validate(DeliveryCreated event){

        if (event.value() < 0){
            log.info("The value of DeliveryCreated cannot be less than 0");
            return false;
        }

        if (deliveryIsAlreadyCreated(event.deliveryId())){
            log.info("This event was already processed");
            return false;
        }

        return true;
    }

    private boolean validate(AdjustmentModified event){

        if (!deliveryIsAlreadyCreated(event.deliveryId())){
            log.info("There is not delivery created for this id.");
            return false;
        }

        return true;
    }

    private boolean validate(BonusModified event){

        if (event.value() < 0){
            log.info("The value of BonusModified cannot be less than 0");
            return false;
        }

        if (!deliveryIsAlreadyCreated(event.deliveryId())){
            log.info("There is not delivery created for this id.");
            return false;
        }

        return true;
    }

}
