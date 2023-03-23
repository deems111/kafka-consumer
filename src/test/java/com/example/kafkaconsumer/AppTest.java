package com.example.kafkaconsumer;

import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.data.entity.WordData;
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

    public WordData getEntity() {
        var wd = new WordData();
        wd.setWord(DEFAULT_WORD);
        wd.setId(DEFAULT_ID);
        return wd;
    }
}
