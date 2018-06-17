package com.kalomiris.service;

import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

public interface OwnerService {
    Owner createOwner(String name, String lastName, String ownerId);

    Owner retrieveOwner(String ownerId);

    void deleteOwner(Owner owner);

    boolean isExistedOwner(String ownerId);

    double calculateChargePerVehicle(Owner owner, Vehicle vehicle);

    int counterVehicle(String ownerId);

}
