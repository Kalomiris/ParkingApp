package com.kalomiris.model;

public class Car extends Vehicle {

    public Car(String numberPlate) {
        super(numberPlate);
    }

    public double chargePerHour() {
        return 5.8;
    }

}
