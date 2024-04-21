package com.felipe.TabelaFipe.service;

public interface IConvertData {
    <T> T convert(String json, Class<T> tClass);
}
