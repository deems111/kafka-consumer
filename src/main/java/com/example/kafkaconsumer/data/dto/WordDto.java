package com.example.kafkaconsumer.data.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Dto - message from Kafka
 */
@Data
public class WordDto implements Serializable {

    private UUID id;
    private String word;
}
