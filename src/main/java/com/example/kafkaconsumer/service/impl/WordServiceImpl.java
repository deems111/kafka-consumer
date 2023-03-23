package com.example.kafkaconsumer.service.impl;

import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.mapper.WordMapper;
import com.example.kafkaconsumer.repository.WordDataRepository;
import com.example.kafkaconsumer.service.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class WordServiceImpl implements WordService {
    private final WordMapper mapper;
    private final WordDataRepository repository;

    /**
     * Convert WordDto to entity to database
     * @param data - message from Kafka
     */
    @Override
    @Transactional
    public void save(WordDto data) {
        var result = repository.save(mapper.toEntity(data));
        log.info("Data saved " + result);
    }
}