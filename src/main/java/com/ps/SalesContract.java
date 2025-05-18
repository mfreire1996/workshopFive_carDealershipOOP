package com.ps;

public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String name, String email, Vehicle vehicleSold, boolean finance){
        super(date, name, email, vehicleSold);
        this.finance = finance;
    }

    public boolean isFinanced() {
        return finance;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + getSalesTax() + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;

        double interestRate = 0.0425;
        int months = 24;

        if (getVehicle().getPrice() > 10000) {
            interestRate = 0.0525;
            months = 48;
        }

        double principal = getTotalPrice();
        double monthlyInterest = interestRate / 12;
        return (principal * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, -months));
    }

    @Override
    public double getPrice() {
        return getTotalPrice();
    }

    @Override
    public double getMonthlyPaymentAbstract() {
        return getMonthlyPayment();
    }

    public double getSalesTax() {
        return getVehicle().getPrice() * 0.05;
    }

    public double getRecordingFee() {
        return 100.00;
    }

    public double getProcessingFee() {
        return  295.00;
    }

    public boolean getFinancingOption(){
        return finance;
    }

}
