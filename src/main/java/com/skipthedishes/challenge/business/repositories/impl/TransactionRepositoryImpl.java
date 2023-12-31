package com.skipthedishes.challenge.business.repositories.impl;

import com.skipthedishes.challenge.business.entities.Transaction;
import com.skipthedishes.challenge.business.repositories.TransactionRepository;
import com.skipthedishes.challenge.business.repositories.mapper.TransactionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    private static final Logger log = Logger.getLogger(TransactionRepositoryImpl.class.getName());

    @Override
    public int save(Transaction transaction) {
        return jdbcTemplate.update(
        "INSERT INTO transaction (id, delivery_id, type, transaction_reference_id, occurred_at, value)" +
            "VALUES(?,?,?,?,?,?)", new Object[] {
                    transaction.id(),
                    transaction.deliveryId(),
                    transaction.type().name(),
                    transaction.transactionReferenceId(),
                    Timestamp.from(transaction.occurredAt()),
                    transaction.value()
            }
        );
    }

    @Override
    public Transaction findById(UUID TransactionId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Transaction WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Transaction.class), TransactionId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Transaction findByReferenceId(UUID referenceId) {
        return jdbcTemplate.queryForObject("SELECT *" +
                        "FROM transaction_reference_id t " +
                        "JOIN delivery d on t.delivery_id = d.id " +
                        "WHERE transaction_reference_id =? ",
                new TransactionRowMapper(),
                referenceId
        );
    }

    @Override
    public List<Transaction> findAllByDeliveryId(UUID deliveryId) {
        return jdbcTemplate.query("SELECT *" +
                        "FROM transaction t " +
                        "JOIN delivery d on t.delivery_id = d.id " +
                        "WHERE delivery_id=? ",
                new TransactionRowMapper(),
                deliveryId
        );
    }

    @Override
    public List<Transaction> findAll() {
        return jdbcTemplate.query("SELECT * from Transaction", BeanPropertyRowMapper.newInstance(Transaction.class));
    }

    public List<Transaction> findAllByCourierAndPeriod(UUID courierId, Date start_at, Date end_at){
        return jdbcTemplate.query("SELECT * FROM transaction t " +
                        "JOIN delivery d on t.delivery_id = d.id " +
                        "WHERE courier_id=? " +
                        "AND occurred_at BETWEEN ? AND ?",
                new TransactionRowMapper(),
                courierId, start_at, end_at
        );
    }

    public List<Transaction> findAllByCourier(UUID courierId){
        return jdbcTemplate.query("SELECT *, DATE_PART('week', occurred_at) AS week_number " +
                        "FROM transaction t " +
                        "JOIN delivery d on t.delivery_id = d.id " +
                        "WHERE courier_id=? ",
                new TransactionRowMapper(),
                courierId
        );
    }

}
