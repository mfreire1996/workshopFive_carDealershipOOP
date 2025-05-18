package com.ps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        String contractLine = "";

        // Common fields
        String date = contract.getDate();
        String name = contract.getName();
        String email = contract.getEmail();
        Vehicle vehicle = contract.getVehicle();
        String vehicleInfo = String.format("%d %d %s %s %s %s %d %.2f",
                vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(),
                vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice()
        );

        if (contract instanceof SalesContract sales) {
            contractLine = String.format(
                    "%s|%s|%s|SALE|%s|%.2f|%.2f|%.2f|%b\n",
                    date, name, email, vehicleInfo,
                    sales.getTotalPrice(), sales.getMonthlyPayment(),
                    sales.getProcessingFee(), sales.getFinancingOption()
            );
        } else if (contract instanceof LeaseContract lease) {
            contractLine = String.format(
                    "%s|%s|%s|LEASE|%s|%.2f|%.2f|%.2f\n",
                    date, name, email, vehicleInfo,
                    lease.getTotalPrice(), lease.getMonthlyPayment(),
                    lease.getLeaseFee()
            );
        }

        try {
            Files.write(Path.of("contracts.csv"), contractLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
