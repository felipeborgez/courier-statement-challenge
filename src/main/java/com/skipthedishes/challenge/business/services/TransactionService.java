package com.skipthedishes.challenge.business.services;

import com.skipthedishes.challenge.api.responses.WeeklyStatementResponse;
import com.skipthedishes.challenge.business.entities.Transaction;
import com.skipthedishes.challenge.business.repositories.TransactionRepository;
import com.skipthedishes.challenge.business.vo.DeliveryTransaction;
import com.skipthedishes.challenge.business.vo.WeekPeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repository;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public List<DeliveryTransaction> getDeliveryTransactions(UUID courierId, Date start_at, Date end_at) {
        return calculateDeliveryTransaction(
            repository.findAllByCourierAndPeriod(courierId, start_at, end_at)
        );
    }

    public List<WeeklyStatementResponse> getWeeklyStatement(UUID courierId) {
        return calculateWeeklyStatement(
            repository.findAllByCourier(courierId)
        );
    }

    public List<DeliveryTransaction> calculateDeliveryTransaction(List<Transaction> list){
        Map<UUID, List<Transaction>> map = list.stream().collect(
            Collectors.groupingBy(Transaction::deliveryId)
        );

        List<DeliveryTransaction> deliveryList = new ArrayList<>();
        map.forEach( (deliveryId, transactions) -> {
            deliveryList.add(new DeliveryTransaction(
                deliveryId,
                transactions.stream().mapToInt(Transaction::value).sum(),
                transactions.stream().filter( t -> t.type() == Transaction.Type.ADJUSTMENT).mapToInt(Transaction::value).sum(),
                transactions.stream().filter( t -> t.type() == Transaction.Type.BONUS).mapToInt(Transaction::value).sum()
            ));
        });
        return deliveryList;
    }

    public List<WeeklyStatementResponse> calculateWeeklyStatement(List<Transaction> list){
        Map<Integer, List<Transaction>> map = list.stream().collect(
            Collectors.groupingBy( t -> t.getWeekPeriod().number())
        );

        List<WeeklyStatementResponse> statementList = new ArrayList<>();
        map.forEach( (uuid, transactions) -> {
            WeekPeriod period = transactions.stream().findFirst().get().getWeekPeriod();

            statementList.add(new WeeklyStatementResponse(
                period.firstDate().toString(),
                period.lastDate().toString(),
                transactions.stream().mapToInt(Transaction::value).sum(),
                transactions.stream().filter( t -> t.type() == Transaction.Type.ADJUSTMENT).mapToInt(Transaction::value).sum(),
                transactions.stream().filter( t -> t.type() == Transaction.Type.BONUS).mapToInt(Transaction::value).sum()
            ));
        });
        return statementList;
    }

}

