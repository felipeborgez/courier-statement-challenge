package com.skipthedishes.challenge.messaging.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.challenge.api.events.AdjustmentModified;
import com.skipthedishes.challenge.api.events.DeliveryCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public void sendMessageToTopic(String topic, String message){
        CompletableFuture<SendResult<String, String>> future = template.send(topic, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendEventsToTopic(String topic, DeliveryCreated event) {
        try {
            CompletableFuture<SendResult<String, String>> future = template.send(topic, objectMapper.writeValueAsString(event));
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + event.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            event.toString() + "] due to : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
}
