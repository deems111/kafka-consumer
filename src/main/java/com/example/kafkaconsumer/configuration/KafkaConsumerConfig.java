package com.example.kafkaconsumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.BytesJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${kafka.consumer.interval}")
    private Long interval;

    @Value("${kafka.consumer.maxAttemps}")
    private Long maxAttemps;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServerAddress;

    @Value("${kafka.consumer.groupId}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, byte[]> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                getConsumerProperties(),
                new StringDeserializer(),
                new ByteArrayDeserializer()
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerContainerFactory(ConsumerFactory<String, byte[]> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setRecordMessageConverter(new BytesJsonMessageConverter(objectMapper));
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(interval, maxAttemps)));
        return factory;
    }

    private Map<String, Object> getConsumerProperties() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddress,
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true,
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, 1000,
                ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 1000
        );
    }
}
