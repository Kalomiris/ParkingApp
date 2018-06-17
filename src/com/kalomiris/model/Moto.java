package com.kalomiris.model;

public class Moto extends Vehicle {

    public Moto(String numberPlate) {
        super(numberPlate);
    }

    public double chargePerHour() {
        return 3.0;
    }
}
