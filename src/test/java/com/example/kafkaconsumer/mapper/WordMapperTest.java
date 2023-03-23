package com.example.kafkaconsumer.mapper;

import com.example.kafkaconsumer.AppTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.kafkaconsumer.util.Utility.DEFAULT_ID;
import static com.example.kafkaconsumer.util.Utility.DEFAULT_WORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WordMapperTest extends AppTest {
    @Autowired
    private WordMapper wordMapper;

    @Test
    @DisplayName("Check wordMapper method to Entity")
    public void testUserMapper() {
        var dto = getDto();
        var result = wordMapper.toEntity(dto);
        assertEquals(result.getId(), DEFAULT_ID);
        assertEquals(result.getWord(), DEFAULT_WORD);
        assertNull(result.getCreated());
    }


}


