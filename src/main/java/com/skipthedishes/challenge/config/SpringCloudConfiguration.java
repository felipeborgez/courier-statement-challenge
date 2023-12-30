package com.skipthedishes.challenge.config;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.skipthedishes.challenge.messaging.consumers.SQSConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
class SpringCloudConfiguration {

    @Value("${cloud.aws.region.static}")
    private String region;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudConfiguration.class);

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
        AmazonSQSAsync amazonSqs
    ){
        LOGGER.info("Starting SimpleMessageListenerContainerFactory");

        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(50);

        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSqs);
        factory.setMaxNumberOfMessages(10);
        factory.setTaskExecutor(executor);
        factory.setWaitTimeOut(20);
        factory.setAutoStartup(true);
        return factory;
    }


    /*@Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        LOGGER.info("Starting Amazon SNS");
        return new NotificationMessagingTemplate(amazonSNS);
    }*/
}
