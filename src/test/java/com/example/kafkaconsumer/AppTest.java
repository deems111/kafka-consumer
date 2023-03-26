package com.example.kafkaconsumer;

import com.example.kafkaconsumer.data.dto.WordDto;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.kafkaconsumer.util.Utility.DEFAULT_ID;
import static com.example.kafkaconsumer.util.Utility.DEFAULT_WORD;

@SpringBootTest
public class AppTest {

    public WordDto getDto() {
        var dto = new WordDto();
        dto.setWord(DEFAULT_WORD);
        dto.setId(DEFAULT_ID);
        return dto;
    }

    public WordDto getDto2() {
        var dto = new WordDto();
        dto.setWord(DEFAULT_WORD + 0);
        dto.setId(DEFAULT_ID);
        return dto;
    }
}
