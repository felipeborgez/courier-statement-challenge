package com.skipthedishes.challenge.business.vo;

import java.time.LocalDate;

public record WeekPeriod (
    int number,
    LocalDate firstDate,
    LocalDate lastDate
){}
