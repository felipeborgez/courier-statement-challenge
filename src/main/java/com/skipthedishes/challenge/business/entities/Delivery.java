package com.skipthedishes.challenge.business.entities;

import java.util.UUID;

public record Delivery(
    UUID id,
    UUID courierId
){}
