package com.ps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init() {
        dealership = DealershipFileManager.getDealership();
    }

    public UserInterface() {
        init();
    }

    public void display() {

        System.out.println("Welcome to the dealership program");

        int mainMenuCommand;

        do {
            System.out.println("1. Get by price");
            System.out.println("2. Get by make/model");
            System.out.println("3. Get by year");
            System.out.println("4. Get by color");
            System.out.println("5. Get by mileage");
            System.out.println("6. Get by type");
            System.out.println("7. Get all");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. SELL/LEASE a vehicle");
            System.out.println("0. Exit");

            System.out.print("Command: ");
            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellOrLeaseVehicle();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Command not found, try again");
            }
        } while (mainMenuCommand != 0);
    }

    private void processGetByPriceRequest() {
        System.out.println("--------Display vehicles by price--------");
        System.out.print("Min: ");
        double min = scanner.nextDouble();

        System.out.print("Max: ");
        double max = scanner.nextDouble();


        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByPrice(min, max);

        displayVehicles(filteredVehicles);
    }

    private void processGetByMakeModelRequest() {
        System.out.println("----See Models----");
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMakeModel(make, model);

        displayVehicles(filteredVehicles);
    }

    private void processGetByYearRequest() {
        System.out.println("----See Years----");
        System.out.print("Enter earliest year: ");
        int min = scanner.nextInt();

        System.out.print("Enter latest year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByYear(min, max);

        displayVehicles(filteredVehicles);
    }

    private void processGetByColorRequest() {
        System.out.println("----See Colors----");
        scanner.nextLine();

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByColor(color);

        displayVehicles(filteredVehicles);
    }

    private void processGetByMileageRequest() {
        System.out.println("----See Mileage----");
        scanner.nextLine();

        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();

        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMileage(minMileage, maxMileage);

        displayVehicles(filteredVehicles);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.println("----See Types----");
        scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByType(type);

        displayVehicles(filteredVehicles);
    }

    private void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("---------Printing all vehicles-----------");
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("----Add Vehicle----");
        scanner.nextLine();

        System.out.print("Add Vin: ");
        int vin = scanner.nextInt();

        System.out.print("Add Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Add Make: ");
        String make = scanner.nextLine();

        System.out.print("Add Model: ");
        String model = scanner.nextLine();

        System.out.print("Add Type: ");
        String type = scanner.nextLine();

        System.out.print("Add Color: ");
        String color = scanner.nextLine();

        System.out.print("Add Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Add Price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        System.out.println("----Remove Vehicle----");
        scanner.nextLine();

        System.out.print("Enter Vin: ");
        int vin = scanner.nextInt();

        boolean removed = dealership.removeVehicleByVin(vin);

        if (removed) {
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle with VIN: " + vin + " not found!");
        }
    }

    private void processSellOrLeaseVehicle() {
        System.out.println("----SELL OR LEASE A VEHICLE----");
        scanner.nextLine();

        System.out.println("Would you like to sell or lease a vehicle? ");
        System.out.println("1. Sell Vehicle");
        System.out.println("2. Lease Vehicle");

        System.out.print("Command: ");

        int sellOrLeaseMenu = scanner.nextInt();

        switch (sellOrLeaseMenu) {
            case 1:
                sellVehicle();
                break;
            case 2:
                leaseVehicle();
                break;
            default:
                System.out.println("Invalid option. Please enter 1 or 2.");
                break;
        }
    }

    private void sellVehicle() {
        scanner.nextLine();

        System.out.print("Enter VIN of the vehicle to sell: ");
        int vin = scanner.nextInt();
        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter buyer's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter buyer's email: ");
        String email = scanner.nextLine();

        System.out.print("Is the vehicle being financed (yes/no): ");
        String financedInput = scanner.nextLine().trim().toLowerCase();

        boolean isFinanced = false;
        if (financedInput.equals("yes")) {
            isFinanced = true;
        } else if (!financedInput.equals("no")) {
            System.out.println("Invalid input. Assuming no financing.");
            isFinanced = false;
        }

        String date = java.time.LocalDate.now().toString();
        SalesContract contract = new SalesContract(date, name, email, vehicle, isFinanced);

        new ContractFileManager().saveContract(contract);
        dealership.removeVehicleByVin(vin);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle sold and contract saved.");
        System.out.println("-------- Sales Contract Summary --------");
        System.out.printf("Buyer: %s%n", contract.getName());
        System.out.printf("Email: %s%n", contract.getEmail());
        System.out.printf("Vehicle: %d %s %s (%s) - $%.2f%n",
                vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                vehicle.getColor(), vehicle.getPrice());
        System.out.printf("Sales Tax: $%.2f%n", contract.getSalesTax());
        System.out.printf("Recording Fee: $%.2f%n", contract.getRecordingFee());
        System.out.printf("Processing Fee: $%.2f%n", contract.getProcessingFee());
        System.out.printf("Total Price: $%.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly Payment: $%.2f%n", contract.getMonthlyPayment());
        System.out.println("Financing: " + (contract.getFinancingOption() ? "YES " : "NO "));
    }



    private void leaseVehicle() {
        System.out.print("Enter VIN of the vehicle to lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        int currentYear = java.time.LocalDate.now().getYear();
        if (vehicle.getYear() < currentYear - 3) {
            System.out.println("Cannot lease a vehicle older than 3 years.");
            return;
        }

        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        System.out.print("Customer email: ");
        String email = scanner.nextLine();

        String date = java.time.LocalDate.now().toString();
        LeaseContract contract = new LeaseContract(date, name, email, vehicle);

        new ContractFileManager().saveContract(contract);
        dealership.removeVehicleByVin(vin);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle leased and contract saved.");
        System.out.println("-------- Lease Contract Summary --------");
        System.out.printf("Customer: %s%n", contract.getName());
        System.out.printf("Email: %s%n", contract.getEmail());
        System.out.printf("Vehicle: %d %s %s (%s) - $%.2f%n",
                vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                vehicle.getColor(), vehicle.getPrice());
        System.out.printf("Lease Fee: $%.2f%n", contract.getLeaseFee());
        System.out.printf("Expected Ending Value: $%.2f%n", vehicle.getPrice() * 0.5);
        System.out.printf("Total Price: $%.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly Payment: $%.2f%n", contract.getMonthlyPayment());
    }

    public static void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle);
        }
    }

}
