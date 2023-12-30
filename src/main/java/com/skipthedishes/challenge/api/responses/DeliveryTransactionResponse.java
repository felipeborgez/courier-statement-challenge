package com.skipthedishes.challenge.api.responses;

public record DeliveryTransactionResponse(
        String weekStartAt,
        String weekEndAt,
        int totalAmountPaidToCourier,
        int totalAmountPaidToCourierDueToAdjustments,
        int totalAmountPaidToCourierDueToBonuses
) { }
