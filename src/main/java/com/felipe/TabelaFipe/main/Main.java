package com.felipe.TabelaFipe.main;


import com.felipe.TabelaFipe.service.ConsumeApi;

import java.util.Scanner;

public class Main {
    private final Scanner reader = new Scanner(System.in);


    public static void main(String[] args) {

    }

    public void showMenu() {
        System.out.println("Hello World!");

        ConsumeApi consume = new ConsumeApi();
        String address = "https://parallelum.com.br/fipe/api/v1/carros/marcas";

        String json = consume.getData(address);
        System.out.println(json);

    }
}
