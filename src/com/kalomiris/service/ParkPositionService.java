package com.kalomiris.service;

import com.kalomiris.model.Vehicle;

public interface ParkPositionService {
    void createParkPosition(Vehicle vehicle);

    void deletePosition(String numberPlate);

    void showDetails(String numberPlate);

    long computeDuration(long time);

    void printPositionMap(int i);
}
