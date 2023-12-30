package com.skipthedishes.challenge.business.repositories.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.challenge.business.old_entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Override
    public Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Transaction(
                UUID.fromString(resultSet.getString("id")),
                UUID.fromString(resultSet.getString("delivery_id")),
                UUID.fromString(resultSet.getString("transaction_reference_id")),
                Transaction.Type.valueOf(resultSet.getString("type")),
                resultSet.getTimestamp("occurred_at").toInstant(),
                resultSet.getInt("value"),
//                    objectMapper.readValue(resultSet.getString("metadata"), new TypeReference<HashMap<String, String>>() {
//                    }),
                resultSet.getTimestamp("inserted_at").toInstant()
        );
    }
}