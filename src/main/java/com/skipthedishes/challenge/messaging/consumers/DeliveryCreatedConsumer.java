package com.skipthedishes.challenge.messaging.consumers;

import com.skipthedishes.challenge.api.events.DeliveryCreated;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DeliveryCreatedConsumer {


//    @Value("${messaging.queue.delivery_created}")
//    private String topicName;

//    @KafkaListener(
//            topics = "courier_event_delivery_created"
//            groupId = "courier-statement-delivery-created"
////        autoStartup = "#{messaging.installment.updated.enabled}"
//    )
    public void readMessage(String message){
//        public void readMessage(@Payload ConsumerRecord<String, DeliveryCreated> message){

//        DeliveryCreated event = message.value();
//        System.out.print(event);

//        fun installmentUpdatedConsumer(@Payload record: ConsumerRecord<String, FinancialContractDomainEvent>) {
//            val financialContractDomainEvent = record.value()
    }


}
