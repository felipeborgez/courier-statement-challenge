package com.skipthedishes.challenge.business.repositories;

import com.skipthedishes.challenge.business.entities.TransactionItem;
import com.skipthedishes.challenge.business.old_entities.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    public int save(Transaction transaction);

    public Transaction findById(UUID transactionId);

    public Transaction findByReferenceId(UUID referenceId);

    public List<Transaction> findAllByDeliveryId(UUID deliveryId);

    public List<Transaction> findAll();

    public List<Transaction> findAllByCourierAndPeriod(UUID courierId, Date start_at, Date end_at);

    public List<Transaction> findAllByCourier(UUID courierId);
}
