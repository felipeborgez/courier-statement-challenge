package com.skipthedishes.challenge.business.entities;

import java.time.Instant;
import java.util.UUID;

public record TransactionItem(
    UUID id,
    Type type,
    UUID reference_id,
    int value,
    Instant occurred_at
) {

    public enum Type {
        CREATION, ADJUSTMENT, BONUS
    }
}
