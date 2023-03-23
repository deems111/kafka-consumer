package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.AppTest;
import com.example.kafkaconsumer.exception.DuplicateException;
import com.example.kafkaconsumer.repository.WordDataRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kafkaconsumer.util.Utility.DEFAULT_ID;
import static com.example.kafkaconsumer.util.Utility.DEFAULT_WORD;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class WordServiceTest extends AppTest {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordDataRepository wordDataRepository;

    @SneakyThrows
    @Test
    @DisplayName("Check saving Word data in Db")
    @Order(1)
    public void testSaving() {
        var count = wordDataRepository.findAll().size();
        assertTrue(wordDataRepository.findById(DEFAULT_ID).isEmpty());
        wordService.save(getDto());
        var results = wordDataRepository.findAll().size();
        var result = wordDataRepository.findById(DEFAULT_ID).get();
        assertEquals(count + 1, results);
        assertEquals(result.getId(), DEFAULT_ID);
        assertEquals(result.getWord(), DEFAULT_WORD);
        assertNotNull(result.getCreated());
    }

    @SneakyThrows
    @Test
    @Transactional
    @DisplayName("Check saving duplicate Word data in Db")
    @Order(2)
    public void testDuplicateSaving() {
        wordService.save(getDto());
        assertThrows(DuplicateException.class, () -> wordService.save(getDto2()));
    }
}
