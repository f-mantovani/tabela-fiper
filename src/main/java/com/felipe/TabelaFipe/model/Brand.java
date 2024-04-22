package com.felipe.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Brand (@JsonAlias("nome") String name, @JsonAlias("codigo") int code) {
    @Override
    public String toString() {
        return """
                Code: %d - Name: %s
                """.formatted(code(), name());
    }
}
