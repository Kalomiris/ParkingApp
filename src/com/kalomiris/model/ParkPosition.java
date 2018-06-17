package com.kalomiris.model;

import java.util.Date;

public class ParkPosition {
    Vehicle vehicle;
    Employee employee;
    Date time;


    public ParkPosition(Vehicle vehicle, Employee employee) {
        this.vehicle = vehicle;
        this.employee = employee;
        time = new Date();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getMyTime() {
        return time;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkPosition that = (ParkPosition) o;

        return vehicle.equals(that.vehicle);
    }

    @Override
    public int hashCode() {
        return vehicle.hashCode();
    }
}
