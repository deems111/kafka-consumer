package com.example.kafkaconsumer.repository;

import com.example.kafkaconsumer.data.entity.WordData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface WordDataRepository extends JpaRepository<WordData, UUID> {

    @Modifying
    void deleteByIdIs(UUID id);

}
