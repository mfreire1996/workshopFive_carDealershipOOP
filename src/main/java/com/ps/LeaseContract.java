package com.ps;

public class LeaseContract extends Contract {
    private double leaseFee;
    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);
    }

    public double getLeaseFee() {
        return getVehicle().getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        int leaseTermMonths = 36;
        double interestRate = 0.04 / 12;

        double principal = getVehicle().getPrice();
        return (principal * interestRate) / (1 - Math.pow(1 + interestRate, -leaseTermMonths));
    }

    @Override
    public double getPrice() {
        return getTotalPrice();
    }

    @Override
    public double getMonthlyPaymentAbstract() {
        return getMonthlyPayment();
    }
}
