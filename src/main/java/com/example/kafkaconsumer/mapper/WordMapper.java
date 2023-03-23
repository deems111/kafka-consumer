package com.example.kafkaconsumer.mapper;

import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.data.entity.WordData;
import org.mapstruct.Mapper;

/**
 * Mapper for converting Entities to Dto and back
 */
@Mapper(componentModel = "spring")
public interface WordMapper {
    WordData toEntity(WordDto dto);
}
