package com.skipthedishes.challenge.api.events;

import com.skipthedishes.challenge.business.old_entities.RawEvent;
import com.skipthedishes.challenge.business.old_entities.Transaction;

import java.util.UUID;

public interface CourierEvent {

    public Transaction convertAsTransaction();
//    public RawEvent convertAsEvent(UUID id, String rawMessage);
}
