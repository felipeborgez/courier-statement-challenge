package com.skipthedishes.challenge.messaging.consumers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

//@Lazy(false)
@Component
public class LocalStackEventConsumer {

    private static final Logger LOGGER = Logger.getLogger(LocalStackEventConsumer.class.getName());

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${messaging.queue.delivery_created}")
    private String delivery_topic_name;

    @SqsListener(value ="${amazon.sqs.queue-name}")
    public void queueListener(String rawMessage) {
//    public void queueListener(Map<String, Object> rawMessage) {
//        SNSMessage message = objectMapper.readValue(rawMessage);
//        val accountServiceToRetain: AccountServiceToRetainResponse = objectMapper.parse(snsMessage.message)
        LOGGER.info("SqsListener Message: " + rawMessage);
//        counter.increment(name.get());
    }

//    @KafkaListener(id = "decrement", topics = TopicProducer.NAME_DECREMENT)
//    public void decrement(Name name) {
//        LOGGER.info("Decrement listener to the name " + name);
//        counter.decrement(name.get());
//    }
}