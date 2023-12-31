package com.skipthedishes.challenge.messaging.publisher;

import com.skipthedishes.challenge.api.events.DeliveryCreated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class QueueService {
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final String queueName;

    private static final Logger log = Logger.getLogger(QueueService.class.getName());

    public QueueService(
        QueueMessagingTemplate queueMessagingTemplate,
        @Value("${messaging.queue.delivery_created}") String queueName
    ) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.queueName = queueName;
    }

    public void publishTask(DeliveryCreated event) {
        log.info("Publishing task to queue + " + event.toString());
        queueMessagingTemplate.convertAndSend(queueName, event);
    }
}
