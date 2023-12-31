package com.skipthedishes.challenge.business.repositories.impl;

import com.skipthedishes.challenge.business.entities.Delivery;
import com.skipthedishes.challenge.business.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    private static final Logger log = Logger.getLogger(TransactionRepositoryImpl.class.getName());

    @Override
    public int save(Delivery delivery) {
        return jdbcTemplate.update(
        "INSERT INTO delivery (id, courier_id)" +
            "VALUES(?,?)", new Object[] {
                    delivery.id(),
                    delivery.courierId()
            }
        );
    }

}
