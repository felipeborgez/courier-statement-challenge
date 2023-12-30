package com.skipthedishes.challenge.business.entities;

import java.util.List;
import java.util.UUID;

public record Transaction(
    UUID id,
    UUID courierId,
    UUID deliveryId,
    List<TransactionItem> items
) {}