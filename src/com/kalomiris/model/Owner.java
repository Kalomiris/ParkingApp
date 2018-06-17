package com.kalomiris.model;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String firstOwnerName;
    private String lastOwnerName;
    private String ownerId;
    private List<Vehicle> vehicleList;

    public Owner(String firstOwnerName, String lastOwnerName, String ownerId) {
        this.firstOwnerName = firstOwnerName;
        this.lastOwnerName = lastOwnerName;
        this.ownerId = ownerId;
        vehicleList = new ArrayList<>();
    }

    public String getFirstOwnerName() {
        return firstOwnerName;
    }

    public String getLastOwnerName() {
        return lastOwnerName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void addInVehicleList(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        return ownerId.equals(owner.ownerId);
    }

    @Override
    public int hashCode() {
        return ownerId.hashCode();
    }
}
