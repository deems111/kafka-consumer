package com.example.kafkaconsumer.kafka;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

/**
 * CamelCase strategy for Object Mapper
 * Using default method nameForField
 */
public class WordNamingStrategy extends PropertyNamingStrategy {

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return getCamelCaseName(method.getName());
    }


    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return getCamelCaseName(method.getName());
    }

    /**
     * Remove first symbols - get
     * Symbol 4 - to lower case
     */
    private String getCamelCaseName(String input) {
        return input.substring(3, 4).toLowerCase().intern() + input.substring(4).intern();
    }

}
