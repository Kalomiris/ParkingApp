package com.kalomiris.dao;

import com.kalomiris.model.ParkPosition;

public interface DaoParkPosition {

    ParkPosition insertRecord(ParkPosition parkPosition, int emptyPosition);

    void deleteRecord(String vehicle_id);

    int EmptyPosition();

    int counterPosition();

    String retrieveAllDetails(String number_Plate);

    long retrieveTime(String number_Plate);


}
