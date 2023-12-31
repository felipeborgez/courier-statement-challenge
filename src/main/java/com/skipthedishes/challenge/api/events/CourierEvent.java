package com.skipthedishes.challenge.api.events;

import com.skipthedishes.challenge.business.entities.Transaction;

public interface CourierEvent {

    public Transaction convertAsTransaction();
//    public RawEvent convertAsEvent(UUID id, String rawMessage);
}
