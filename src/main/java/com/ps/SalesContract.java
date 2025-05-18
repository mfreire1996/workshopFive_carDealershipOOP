package com.ps;

public class SalesContract extends Contract {

    if (contract instanceOf SalesContract){
        SalesContract sales = (SalesContract) contract;

        contractLine = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f\\n\"," +
                date,
                name,
                email,
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice(),
                sales.getTotalPrice(),
                sales.getTaxAmount(),
                sales.getRecordingFee(),
                sales.getRecordingFee(),
                sales.getProcessingFee(),
                sales.getMonthlyPayment(),
                sales.getFiancingOption(),
                sales.getMonthlyPayment()
                );
    }
//
//    public double getPrice();
//    public double getMonthlyPayment();
}
