package com.example.kafkaconsumer.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "test_word_data")
public class WordData {

    @Id
    private UUID id;
    private String word;
    private LocalDateTime created;

    //TODO - create basic entity with columns Id, created, modified
    @PrePersist
    public void create() {
        this.created = LocalDateTime.now();
    }
}
