package com.kalomiris.service;

import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

public interface VehicleService {
    Vehicle createVehicle(String numberPlate, Owner owner, boolean isCar);

    void deleteVehicle(String numberPlate);

    Vehicle retrieveVehicle(String numberPlate);

    boolean isExistedVehicle(String numberPlate);

}
