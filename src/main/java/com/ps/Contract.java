package com.ps;

abstract class Contract {
    private String date;
    private String name;
    private String email;
    private Vehicle vehicle;

    public Contract(String date, String name, String email, Vehicle vehicle) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public abstract double getPrice();
    public abstract double getMonthlyPaymentAbstract();

    public String getContractType(){
        return this instanceof SalesContract ? "SALE" : "LEASE";
    }

}
