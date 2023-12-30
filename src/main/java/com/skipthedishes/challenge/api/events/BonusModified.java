package com.skipthedishes.challenge.api.events;

import com.skipthedishes.challenge.business.old_entities.RawEvent;
import com.skipthedishes.challenge.business.old_entities.Transaction;

import java.time.Instant;
import java.util.UUID;

public record BonusModified(
    UUID bonusId,
    UUID deliveryId,
    Instant modifiedTimestamp,
    int value,
    String raw
) implements CourierEvent {
    @Override
    public Transaction convertAsTransaction() {
        return new Transaction(
            UUID.randomUUID(),
            this.deliveryId,
            this.bonusId(),
            Transaction.Type.BONUS,
            this.modifiedTimestamp,
            this.value,
            Instant.now()
        );
    }

//    @Override
//    public RawEvent convertAsEvent(UUID id, String body){
//        return new RawEvent(
//                id,
//                this.bonusId,
//                body,
//                RawEvent.Status.PENDING,
//                Instant.now()
//        );
//    }
}
