package com.skipthedishes.challenge.business.repositories.impl;

import com.skipthedishes.challenge.business.entities.RawEvent;
import com.skipthedishes.challenge.business.entities.Transaction;
import com.skipthedishes.challenge.business.repositories.EventRepository;
import com.skipthedishes.challenge.business.repositories.mapper.TransactionRowMapper;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = Logger.getLogger(EventRepositoryImpl.class.getName());

    @Override
    public int save(RawEvent rawEvent) {
        return jdbcTemplate.update(
        "INSERT INTO event (id, raw, status, inserted_at)" +
            "VALUES(?,?,?,?)", new Object[] {
                rawEvent.id(),
                castAsJsonB(rawEvent.raw()),
                rawEvent.status().toString(),
                Timestamp.from(rawEvent.insertedAt())
            }
        );
    }

    public int update(UUID id, UUID referenceId, RawEvent.Status status) {
        return jdbcTemplate.update(
            "UPDATE event SET status = ?, reference_id = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ? ", new Object[] {
                status.name(),
                referenceId,
                id
            }
        );
    }

    // @Override
    public Transaction findById(UUID TransactionId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Transaction WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Transaction.class), TransactionId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    // @Override
    public Transaction findByReferenceId(UUID referenceId) {
        return null;
    }

    // @Override
    public List<Transaction> findAllByDeliveryId(UUID deliveryId) {
        return null;
    }

    // @Override
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

    private PGobject castAsJsonB(String json){
        try {
            PGobject rawJsonb = new PGobject();
            rawJsonb.setType("jsonb");
            rawJsonb.setValue(json);
            return rawJsonb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
