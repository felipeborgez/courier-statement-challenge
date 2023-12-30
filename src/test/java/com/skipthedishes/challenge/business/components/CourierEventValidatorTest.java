package com.skipthedishes.challenge.business.components;

import com.skipthedishes.challenge.business.repositories.TransactionRepository;
import com.skipthedishes.challenge.business.services.CourierEventValidator;
import com.skipthedishes.challenge.business.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierEventValidatorTest {

    @MockBean
    private TransactionRepository repository;

    @InjectMocks
    private CourierEventValidator validator;

    /*@Test
    void () {

    }*/

}