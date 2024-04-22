package com.felipe.TabelaFipe.main;


import com.felipe.TabelaFipe.model.Brand;
import com.felipe.TabelaFipe.service.ConsumeApi;
import com.felipe.TabelaFipe.service.ConvertData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner reader = new Scanner(System.in);
    private final ConvertData data = new ConvertData();
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";
    private final ConsumeApi consume = new ConsumeApi();
    public static void main(String[] args) {

    }

    public void showMenu() {
        System.out.println("Check the prices of brazilian car market");
        System.out.println("""
                ***** Options *****
                1. Cars
                2. Motorcycles
                3. Trucks
                
                Press the number related to your choice or ANY other number to exit
                """);
        int option = reader.nextInt();
        reader.nextLine();

        String address;
        String json;
        String toShow;
        if (option == 1) {
            address = BASE_URL + "/carros/marcas/";
            json = consume.getData(address);
            toShow = "car brands";
        } else if (option == 2) {
            address = BASE_URL + "/motos/marcas/";
            json = consume.getData(address);
            toShow = "motorcycle brands";
        } else if ( option == 3) {
            address = BASE_URL + "/caminhoes/marcas/";
            json = consume.getData(address);
            toShow = "truck brands";
        } else {
            System.out.println("Closing application");
            return;
        }



        List<Brand> brands = data.convertList(json, Brand.class);
        List<Brand> ordered = brands.stream().sorted(Comparator.comparing(Brand::code)).toList();

        System.out.println("Showing " + toShow + " - choose typing the code from the brand");
        ordered.forEach(b -> {
            System.out.printf("%d - %s %n", b.code(), b.name());
        });

        int codeOption = reader.nextInt();
        reader.nextLine();

        json = consume.getData(address + codeOption + "/modelos");
        System.out.println(json);


    }
}
