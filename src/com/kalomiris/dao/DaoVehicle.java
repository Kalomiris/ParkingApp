package com.kalomiris.dao;

import com.kalomiris.model.Vehicle;

public interface DaoVehicle {

    public Vehicle insertRecord(Vehicle vehicle);

    Vehicle retrieveRecord(String number_plate);

    void removeRecord(String number_plate);

    boolean isExisted(String numberPlate);
}

