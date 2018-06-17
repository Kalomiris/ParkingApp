package com.kalomiris.dao;

import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

import java.util.ArrayList;

public interface DaoOwner {

    Owner insertRecord(Owner owner);

    Owner retrieveRecord(String owner_id);

    void removeRecord(String ownerId);

    ArrayList<Vehicle> retrieveVehicleList(String  ownerId);

    boolean isExisted(String id);
}
