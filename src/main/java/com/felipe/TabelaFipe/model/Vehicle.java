package com.felipe.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Modelo") String name,
                      @JsonAlias("Marca") String brand,
                      @JsonAlias("Combustivel") String fuelType,
                      @JsonAlias("AnoModelo") int year,
                      @JsonAlias( "Valor") String price) {
    @Override
    public String toString() {
        return """
                %s from %s, year: %d, pricing %s, uses %s as fuel
                """.formatted(name(), brand(), year(), price(), fuelType());
    }
}
