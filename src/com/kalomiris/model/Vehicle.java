package com.kalomiris.model;

abstract public class Vehicle {
    private String numberPlate;
    private Owner owner;

    public Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return numberPlate.equals(vehicle.numberPlate);
    }

    @Override
    public int hashCode() {
        return numberPlate.hashCode();
    }
}
