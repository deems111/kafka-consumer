package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.AppTest;
import com.example.kafkaconsumer.kafka.WordListener;
import com.example.kafkaconsumer.repository.WordDataRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.kafkaconsumer.util.Utility.DEFAULT_ID;
import static com.example.kafkaconsumer.util.Utility.DEFAULT_WORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class KafkaWordListenerTest extends AppTest {

    @Autowired
    private WordListener wordListener;

    @Autowired
    private WordDataRepository wordDataRepository;


    @Test
    @DisplayName("Test Word Listener and Saving in Db")
    public void testWordListener() {
        var dto = getDto();
        var now = LocalDateTime.now();
        wordListener.listener(dto);

        var result = wordDataRepository.findById(DEFAULT_ID).get();

        assertEquals(result.getId(), DEFAULT_ID);
        assertEquals(result.getWord(), DEFAULT_WORD);
        assertTrue(result.getCreated().isAfter(now));
    }

    @AfterEach
    public void clear(){
        wordDataRepository.deleteByIdIs(DEFAULT_ID);
    }
}
