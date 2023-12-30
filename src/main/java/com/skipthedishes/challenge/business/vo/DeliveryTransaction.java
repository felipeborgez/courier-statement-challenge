package com.skipthedishes.challenge.business.vo;

import java.util.UUID;

public record DeliveryTransaction(
    UUID deliveryId,
    int sumTotal,
    int sumAdjustments,
    int sumBonuses
) {}
