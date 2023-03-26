package com.example.kafkaconsumer.service.impl;

import com.example.kafkaconsumer.data.dto.WordDto;
import com.example.kafkaconsumer.exception.CustomValidationException;
import com.example.kafkaconsumer.exception.DuplicateException;
import com.example.kafkaconsumer.mapper.WordMapper;
import com.example.kafkaconsumer.repository.WordDataRepository;
import com.example.kafkaconsumer.service.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.kafkaconsumer.exception.ExceptionUtils.DEFAULT_ERROR_TEXT;

@Slf4j
@AllArgsConstructor
@Service
public class WordServiceImpl implements WordService {
    private final WordMapper mapper;
    private final WordDataRepository repository;

    /**
     * Convert WordDto to entity to database
     *
     * @param dto - message from Kafka
     */
    @Override
    @Transactional
    public void process(WordDto dto) {
        try {
            validate(dto);
            checkDuplicate(dto);
            save(dto);
        } catch (Exception e) {
            processException();
        }
    }

    //Create Validator only with one method - too much
    private void validate(WordDto dto) throws CustomValidationException {
        if (dto.getId() == null) {
            var message = "Id is Null";
            log.error(DEFAULT_ERROR_TEXT + message);
            throw new CustomValidationException(message);
        }
    }

    private void checkDuplicate(WordDto dto) throws DuplicateException {
        var id = dto.getId();
        if (repository.findById(id).isPresent()) {
            var message = "Duplicate Id = " + id;
            log.error(DEFAULT_ERROR_TEXT + message);
            throw new DuplicateException(message);
        }
    }

    private void save(WordDto dto) {
        var result = repository.save(mapper.toEntity(dto));
        log.info("Data saved " + result);
    }

    private void processException() {
        //TODO - processing exceptions - save in Db / ignore / notify developers or support
    }

}
