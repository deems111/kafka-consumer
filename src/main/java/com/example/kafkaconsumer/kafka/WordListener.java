package com.example.kafkaconsumer.kafka;


import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.exception.DuplicateException;
import com.example.kafkaconsumer.service.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Listener for getting Messages From Kafka
 */
@Slf4j
@Component
@AllArgsConstructor
public class WordListener {
    private final WordService wordService;

    //TODO - multi group support with @KafkaHandler + define @PartitionOffset / Topic Partition
    @KafkaListener(topics = "${kafka.topic.name}", autoStartup = "${kafka.consumer.autoStartup}")
    public void listener(WordDto dto) {
        var id = dto.getId();
        log.info("Start processing message with id = " + id);
        wordService.process(dto);
        log.info("Finished processing message with id = " + id);
    }
}
