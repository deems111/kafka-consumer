package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.data.dto.WordDto;

public interface WordService {
    void process(WordDto data);
}
