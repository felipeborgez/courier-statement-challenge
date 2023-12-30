package com.skipthedishes.challenge.messaging.consumers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SQSConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSConsumer.class);

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private ObjectMapper objectMapper;

    @SqsListener("${messaging.queue.delivery_created}")
    public void receiveDeliveryEvent(Map<String, Object> message) {
        LOGGER.info("SQS Message Received : {}", message);
    }
}

