package com.skipthedishes.challenge.business.entities;

import com.skipthedishes.challenge.business.vo.WeekPeriod;

import java.time.*;
import java.time.temporal.WeekFields;
import java.util.UUID;

public record Transaction(
    UUID id,
    UUID deliveryId,
    UUID transactionReferenceId,
    Transaction.Type type,
    Instant occurredAt,
    int value,
    Instant insertedAt
) {



    public enum Type {
        CREATION, ADJUSTMENT, BONUS
    }

    public WeekPeriod getWeekPeriod(){
        ZonedDateTime zonedDateTime = occurredAt.atZone(ZoneId.of("UTC"));

        LocalDate firstDateOfWeek = zonedDateTime.toLocalDate()
                .with(DayOfWeek.SUNDAY);

        return new WeekPeriod(
            zonedDateTime.get(WeekFields.ISO.weekOfWeekBasedYear()), // week number
            firstDateOfWeek,
            firstDateOfWeek.plusDays(6)
        );
    }
}