package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


public class ContractFileManager {

    public List<Contract> loadContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");


                String date = parts[0];
                String name = parts[1];
                String email = parts[2];
                String contractType = parts[3];

                String[] vehicleData = parts[4].split(" ");
                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String type = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                if (contractType.equals("SALE")) {
                    boolean financed = Boolean.parseBoolean(parts[8]);
                    SalesContract sale = new SalesContract(date, name, email, vehicle, financed);
                    contracts.add(sale);
                } else if (contractType.equals("LEASE")) {
                    LeaseContract lease = new LeaseContract(date, name, email, vehicle);
                    contracts.add(lease);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    public void saveContract(Contract contract) {
        String contractLine = "";

        String date = contract.getDate();
        String name = contract.getName();
        String email = contract.getEmail();
        Vehicle vehicle = contract.getVehicle();
        String vehicleInfo = String.format("%d %d %s %s %s %s %d %.2f",
                vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice()
        );

        if (contract instanceof SalesContract sales) {
            contractLine = String.format(
                    "%s|%s|%s|SALE|%s|%b\n",
                    date, name, email, vehicleInfo,
                    sales.isFinanced()
            );
        } else if (contract instanceof LeaseContract) {
            contractLine = String.format(
                    "%s|%s|%s|LEASE|%s\n",
                    date, name, email, vehicleInfo
            );
        }

        try {
            Files.write(
                    Path.of("contracts.csv"),
                    contractLine.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
