package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.AppTest;
import com.example.kafkaconsumer.repository.WordDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kafkaconsumer.util.Utility.DEFAULT_ID;
import static com.example.kafkaconsumer.util.Utility.DEFAULT_WORD;
import static org.junit.jupiter.api.Assertions.*;

public class WordServiceTest extends AppTest {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordDataRepository wordDataRepository;

    @Test
    @Transactional
    @DisplayName("Check saving Word data in Db")
    @Order(1)
    public void testSaving() {
        var count = wordDataRepository.findAll().size();
        assertTrue(wordDataRepository.findById(DEFAULT_ID).isEmpty());
        wordService.save(getDto());
        var results = wordDataRepository.findAll().size();
        System.out.println(results);
        var result = wordDataRepository.findById(DEFAULT_ID).get();
        assertEquals(count + 1, results);
        assertEquals(result.getId(), DEFAULT_ID);
        assertEquals(result.getWord(), DEFAULT_WORD);
        assertNotNull(result.getCreated());
    }

    @Test
    @Transactional
    @DisplayName("Check saving duplicate Word data in Db")
    @Order(2)
    public void testDuplicateSaving() {
        var count = wordDataRepository.findAll();
        assertTrue(wordDataRepository.findById(DEFAULT_ID).isEmpty());
        wordService.save(getDto());
        var results = wordDataRepository.findAll();
        System.out.println(results);
        var result = wordDataRepository.findById(DEFAULT_ID).get();
        assertEquals(result.getId(), DEFAULT_ID);
        assertEquals(result.getWord(), DEFAULT_WORD);
        assertNotNull(result.getCreated());
    }
}
