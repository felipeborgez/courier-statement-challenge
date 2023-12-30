package com.skipthedishes.challenge.messaging.publisher;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.challenge.business.old_entities.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

//@Lazy(false)
@Component
public class EventPublisher {

    @Value("${messaging.queue.delivery_created}")
    private String deliveryCreatedTopic;
    @Value("${messaging.queue.adjustment_modified}")
    private String adjustmentModifiedTopic;
    @Value("${messaging.queue.bonus_modified}")
    private String bonusModifiedTopic;

    private static final Logger LOGGER = Logger.getLogger(EventPublisher.class.getName());

    private QueueMessagingTemplate queueMessagingTemplate;
//    private SqsQueueNames sqsQueueNames;

    @Value(value = "${messaging.queue.delivery_created}")
    private String delivery_topic_name;

    @Autowired
    public void SqsQueueSender(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSqs);
    }

    public void send(String message) {
        this.queueMessagingTemplate.send("physicalQueueName", MessageBuilder.withPayload(message).build());
    }

    public void send(Delivery delivery){
        try {
            ObjectMapper mapper = new ObjectMapper()
//            registerModule(new JSR310Module())
                    .findAndRegisterModules();
            String message = mapper.writeValueAsString(delivery);
            queueMessagingTemplate.convertAndSend(delivery_topic_name, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    public void sendMessage(String message) {
//        sqsAsyncClient.sendMessage(SendMessageRequest.builder()
//                .queueUrl(queueUrl)
//                .messageBody(message)
//                .build());
//    }
}