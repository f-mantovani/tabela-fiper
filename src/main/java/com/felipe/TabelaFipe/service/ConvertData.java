package com.felipe.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConvertData implements IConvertData {
    private final ObjectMapper om = new ObjectMapper();

    @Override
    public <T> T convert(String json, Class<T> tClass) {
        try {
            return om.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> convertList(String json, Class<T> tClass) {
        CollectionType list = om.getTypeFactory()
                .constructCollectionType(List.class, tClass);

        try {
            return om.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
