package com.skipthedishes.challenge.api.responses;

import java.time.LocalDate;

public record WeeklyStatementResponse(
        String weekStartAt,
        String weekEndAt,
        int totalAmountPaidToCourier,
        int totalAmountPaidToCourierDueToAdjustments,
        int totalAmountPaidToCourierDueToBonuses
) { }
