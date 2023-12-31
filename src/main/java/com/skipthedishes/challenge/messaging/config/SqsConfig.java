package com.skipthedishes.challenge.messaging.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class SqsConfig {
    private final int BATCH_SIZE = 10;
    private final String queueEndpoint;
    private final String queueRegion;
    private final String awsAccessKey;
    private final String awsSecretKey;

    public SqsConfig(
            @Value("${cloud.aws.endpoint.url}") String queueEndpoint,
            @Value("${cloud.aws.region.static}") String queueRegion,
            @Value("${cloud.aws.credentials.access-key}") String awsAccessKey,
            @Value("${cloud.aws.credentials.secret-key}") String awsSecretKey
            ) {
        this.queueEndpoint = queueEndpoint;
        this.queueRegion = queueRegion;
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SqsConfig.class);


    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync(){
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(queueEndpoint, queueRegion)
            )
            .build();
    }


    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync, CustomMessageConverter customMessageConverter) {
//        QueueMessagingTemplate queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
//        queueMessagingTemplate.setMessageConverter(customMessageConverter);
//        return queueMessagingTemplate;
//    }

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
}
