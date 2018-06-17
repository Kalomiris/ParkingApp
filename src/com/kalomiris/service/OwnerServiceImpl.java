package com.kalomiris.service;

import com.kalomiris.dao.DaoOwnerImpl;
import com.kalomiris.dao.DaoParkpositionImpl;
import com.kalomiris.model.Car;
import com.kalomiris.model.Moto;
import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

public class OwnerServiceImpl implements OwnerService {

    private static ParkPositionServiceImpl parkPositionServiceImpl = new ParkPositionServiceImpl();
    private DaoOwnerImpl daoOwnerImpl = new DaoOwnerImpl();
    private DaoParkpositionImpl daoParkpositionImpl = new DaoParkpositionImpl();
    /**
     * Create owner (if does not exist)
     */
    public Owner createOwner(String firstOwnerName, String lastOwnerName, String ownerId) {
        try {
            if (!daoOwnerImpl.isExisted(ownerId)) {
                Owner newOwner = new Owner(firstOwnerName, lastOwnerName, ownerId);
                System.out.println("Your registration is completed, please enter");
                daoOwnerImpl.insertRecord(newOwner);   //insert in db.
                return retrieveOwner(ownerId);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Retrieve existed owner
     */
    public Owner retrieveOwner(String ownerId) {
        if (isExistedOwner(ownerId)) {
            return daoOwnerImpl.retrieveRecord(ownerId);
        }
        return null;
    }

//    /**
//     * Retrieve car list of specific owner
//     */
//    public List<Vehicle>retrieveVehicleList(){
//
//    }

    /**
     * Delete owner
     */
    public void deleteOwner(Owner owner){
        //if his vehicle is 0, owner is deleted
        if (owner.getVehicleList().size() < 1){
            daoOwnerImpl.removeRecord(owner.getOwnerId());
        }

    }


    /**
     * Check for if a owner is existed in db
     */
    public boolean isExistedOwner(String ownerId) {
        try {
            if (daoOwnerImpl.isExisted(ownerId)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Calculate cost of specific parking position which contains a owner 's vehicle (input in method)
     * Caution! Duration is in Millisecond, so must convert in minute and then check for type of vehicle and the charge.
     * Finally return the cost per POSITION(Vehicle) in Owner
     */
    public double calculateChargePerVehicle(Owner owner, Vehicle vehicle) {
        double duration = parkPositionServiceImpl.computeDuration(daoParkpositionImpl.retrieveTime(vehicle.getNumberPlate())) / 60000;
        if (duration < 30) {
            if (counterVehicle(owner.getOwnerId()) >= 2) {      //Counter for number of vehicle per owner.
                if (vehicle instanceof Car) {
                    double costForCar = ((Car) vehicle).chargePerHour() * duration;
                    return costForCar - 0.3 * costForCar;
                } else {
                    double costOfMoto = ((Moto) vehicle).chargePerHour() * duration;
                    return costOfMoto - 03 * costOfMoto;
                }
            } else {
                if (vehicle instanceof Car) {
                    return ((Car) vehicle).chargePerHour() * duration;
                } else {
                    return ((Moto) vehicle).chargePerHour() * duration;
                }
            }
        }else{
            return 0.0;
        }

    }

    public int counterVehicle(String ownerId){
        return daoOwnerImpl.retrieveVehicleList(ownerId).size();
    }
}
