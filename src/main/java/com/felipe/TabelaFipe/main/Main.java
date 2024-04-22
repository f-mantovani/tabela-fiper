package com.felipe.TabelaFipe.main;


import com.felipe.TabelaFipe.model.Data;
import com.felipe.TabelaFipe.model.Model;
import com.felipe.TabelaFipe.model.Vehicle;
import com.felipe.TabelaFipe.service.ConsumeApi;
import com.felipe.TabelaFipe.service.ConvertData;

import java.util.ArrayList;
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

        List<Data> brands = data.convertList(json, Data.class);
        System.out.println("Showing " + toShow + " - choose typing the code from the brand");
        brands.stream()
                .sorted(Comparator.comparing(Data::codeAsInt))
                .forEach(System.out::print);

        int codeOption = reader.nextInt();
        reader.nextLine();

        address = address + codeOption + "/modelos/";
        json = consume.getData(address);
        Model modelsList = data.convert(json, Model.class);
        modelsList.models().stream()
                .sorted(Comparator.comparing(Data::codeAsInt))
                .forEach(System.out::print);


        int modelOption = reader.nextInt();
        reader.nextLine();

        List<Data> found = modelsList.models().stream()
                .filter(m -> Integer.parseInt(m.code()) == modelOption)
                .toList();



        address = address + found.get(0).code() + "/anos/";
        json = consume.getData(address);
        List<Data> years = data.convertList(json, Data.class);

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < years.size(); i += 1) {
            json = consume.getData(address + years.get(i).code());
            Vehicle vehicle = data.convert(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        vehicles.forEach(System.out::print);



    }
}
