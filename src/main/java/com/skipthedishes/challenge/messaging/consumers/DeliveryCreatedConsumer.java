package com.skipthedishes.challenge.messaging.consumers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.skipthedishes.challenge.messaging.publisher.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
//@EnableSqs
public class DeliveryCreatedConsumer {

//    @Autowired
//    AmazonSQS amazonSQS;

    private static final Logger log = Logger.getLogger(DeliveryCreatedConsumer.class.getName());

    @SqsListener(value = "courier_event_delivery_created",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void readMessage(String message) {
        log.info("SQS Message Received:  " +  message);
//        amazonSQS.receiveMessage("courier_event_delivery_created").getMessages();
    }


}
