package com.skipthedishes.challenge.business.old_entities;

import org.postgresql.util.PGobject;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public record RawEvent(
        UUID id,
        UUID reference_id,
        String raw,
        RawEvent.Status status,
        Instant insertedAt
) {


    public enum Status {
        PENDING, PROCESSED, ERROR, SKIPPED
    }

}
