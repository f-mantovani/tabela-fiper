package com.felipe.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(@JsonAlias("nome") String name, @JsonAlias("codigo") String code) {
    @Override
    public String toString() {
        return """
                %s - %s
                """.formatted(code(), name());
    }

    public int codeAsInt() {
        return Integer.parseInt(code());
    }


}
