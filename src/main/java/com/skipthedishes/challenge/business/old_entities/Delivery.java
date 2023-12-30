package com.skipthedishes.challenge.business.old_entities;

import java.time.Instant;
import java.util.UUID;

public record Delivery(
    UUID id,
    UUID courierId
){}
