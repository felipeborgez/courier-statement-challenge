package com.skipthedishes.challenge.api.events;

import com.skipthedishes.challenge.business.old_entities.RawEvent;
import com.skipthedishes.challenge.business.old_entities.Transaction;

import java.time.Instant;
import java.util.UUID;

public record AdjustmentModified(
    UUID adjustmentId,
    UUID deliveryId,
    Instant modifiedTimestamp,
    int value
) implements CourierEvent {

    @Override
    public Transaction convertAsTransaction() {
        return new Transaction(
                UUID.randomUUID(),
                this.deliveryId,
                this.adjustmentId(),
                Transaction.Type.ADJUSTMENT,
                this.modifiedTimestamp,
                this.value,
                Instant.now()
        );
    }

//    @Override
//    public RawEvent convertAsEvent(UUID id, String body){
//        return new RawEvent(
//            id,
//            this.adjustmentId,
//            body,
//            RawEvent.Status.PENDING,
//            Instant.now()
//        );
//    }
}
