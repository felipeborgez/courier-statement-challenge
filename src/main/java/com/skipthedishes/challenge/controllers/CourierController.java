package com.skipthedishes.challenge.controllers;

import com.skipthedishes.challenge.api.responses.WeeklyStatementResponse;
import com.skipthedishes.challenge.business.services.TransactionService;
import com.skipthedishes.challenge.business.vo.DeliveryTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/couriers")
public class CourierController {

    @Autowired
    TransactionService service;

    private static final Logger log = Logger.getLogger(CourierController.class.getName());


    @GetMapping("/{id}/transactions")
    @ResponseBody
    public List<DeliveryTransaction> getTransactionsByCourier(@PathVariable("id") UUID courierId, @RequestParam Date startAt, @RequestParam Date endAt) {
        log.info("Start getting transactions by courier " + courierId);
        return service.getDeliveryTransactions(courierId, startAt, endAt);
    }

    @GetMapping("/{id}/statement")
    @ResponseBody
    public List<WeeklyStatementResponse> getWeeklyStatement(@PathVariable("id") UUID courierId) {
        log.info("Start getting statement by courier " + courierId);
        return service.getWeeklyStatement(courierId);
    }

}
