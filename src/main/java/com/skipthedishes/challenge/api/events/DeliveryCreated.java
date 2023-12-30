package com.skipthedishes.challenge.api.events;

import com.skipthedishes.challenge.business.old_entities.Delivery;
import com.skipthedishes.challenge.business.old_entities.RawEvent;
import com.skipthedishes.challenge.business.old_entities.Transaction;

import java.time.Instant;
import java.util.UUID;

public record DeliveryCreated (
    UUID deliveryId,
    UUID courierId,
    Instant createdTimestamp,
    int value,
    String raw
) implements CourierEvent {

    public Delivery convertAsDelivery() {
        return new Delivery(
                this.deliveryId,
                courierId()
        );
    }

    @Override
    public Transaction convertAsTransaction() {
        return new Transaction(
            UUID.randomUUID(),
            this.deliveryId,
            this.deliveryId,
            Transaction.Type.CREATION,
            this.createdTimestamp,
            this.value,
            Instant.now()
        );
    }

//    @Override
//    public RawEvent convertAsEvent(UUID id, String body){
//        return new RawEvent(
//                id,
//                this.deliveryId,
//                body,
//                RawEvent.Status.PENDING,
//                Instant.now()
//        );
//    }
}
