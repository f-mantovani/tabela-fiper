package com.felipe.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
