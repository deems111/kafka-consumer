package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.exception.DuplicateException;

public interface WordService {
    void save(WordDto data) throws DuplicateException;
}
