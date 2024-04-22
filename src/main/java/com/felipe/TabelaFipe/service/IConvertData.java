package com.felipe.TabelaFipe.service;

import java.util.List;

public interface IConvertData {
    <T> T convert(String json, Class<T> tClass);

    <T> List<T> convertList (String json, Class<T> tClass);
}
