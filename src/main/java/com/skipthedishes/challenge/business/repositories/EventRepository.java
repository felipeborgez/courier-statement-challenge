package com.skipthedishes.challenge.business.repositories;

import com.skipthedishes.challenge.business.old_entities.RawEvent;

import java.util.UUID;

public interface EventRepository {

    public int save(RawEvent rawEvent);

    public int update(UUID id, UUID referenceId, RawEvent.Status status);

}
