//package com.skipthedishes.challenge.messaging.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.boot.ssl.SslBundles;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
////@Configuration
//public class KafkaProducer {
//
//    final KafkaProperties kafkaProperties;
//
//    public KafkaProducer(KafkaProperties kafkaProperties) {
//        this.kafkaProperties = kafkaProperties;
//    }
//
//
////    @Bean
//    public Map<String, Object> producerConfiguration(ObjectProvider<SslBundles> sslBundles) {
//        Map<String, Object> properties = new HashMap<>(kafkaProperties.buildProducerProperties(sslBundles.getIfAvailable()));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return properties;
//    }
//
////    @Bean
////    ProducerFactory<String, Object> producerFactory() {
////        return new DefaultKafkaProducerFactory<>(producerConfiguration());
////    }
//
////    @Bean
////    KafkaTemplate<String, Object> kafkaTemplate() {
////        return new KafkaTemplate<>(producerFactory());
////    }
//
////    @Bean
//    public NewTopic topic() {
//        return new NewTopic("courier_event_delivery_created", 1, (short) 1);
////        new NewTopic("courier_event_adjustment_modified", 1, (short) 1);
////        new NewTopic("courier_event_bonus_modified", 1, (short) 1);
//    }
//}
