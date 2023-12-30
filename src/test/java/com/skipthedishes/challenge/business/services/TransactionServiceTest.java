package com.skipthedishes.challenge.business.services;

import com.skipthedishes.challenge.business.old_entities.Transaction;
import com.skipthedishes.challenge.business.vo.DeliveryTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    TransactionService service;

    @Test
    void calculateDeliveryTransaction() {
        //given
        UUID deliveryOne = UUID.randomUUID();
        UUID deliveryTwo = UUID.randomUUID();
        UUID deliveryThree = UUID.randomUUID();

        Map<UUID, Integer> assertMap = new HashMap<>();
        assertMap.put(deliveryOne, 1500);
        assertMap.put(deliveryTwo, 600);
        assertMap.put(deliveryThree, 1200);

        List<Transaction> list = List.of(
            transactionMock(deliveryOne, 800, Transaction.Type.CREATION),
            transactionMock(deliveryOne, 500, Transaction.Type.ADJUSTMENT),
            transactionMock(deliveryOne, 200, Transaction.Type.BONUS),

            transactionMock(deliveryTwo, 800, Transaction.Type.CREATION),
            transactionMock(deliveryTwo, -200, Transaction.Type.ADJUSTMENT),

            transactionMock(deliveryThree, 1000, Transaction.Type.CREATION),
            transactionMock(deliveryThree, 200, Transaction.Type.BONUS)
        );

        //when
        List<DeliveryTransaction> result = service.calculateDeliveryTransaction(list);

        //then
        result.forEach( r ->
            assertEquals( assertMap.get(r.deliveryId()), r.sumTotal())
        );
    }

    private Transaction transactionMock(UUID deliveryId, int value, Transaction.Type type){
        return new Transaction(
            UUID.randomUUID(),
            deliveryId,
            UUID.randomUUID(),
            type,
            Instant.now(),
            value,
            Instant.now()
        );
    }
}