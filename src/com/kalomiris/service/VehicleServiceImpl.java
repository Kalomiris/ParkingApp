package com.kalomiris.service;

import com.kalomiris.dao.DaoVehicleImpl;
import com.kalomiris.model.Car;
import com.kalomiris.model.Moto;
import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

public class VehicleServiceImpl implements VehicleService{

    private DaoVehicleImpl daoVehicleImpl = new DaoVehicleImpl();

    /**
     * Create a new vehicle
     */
    public Vehicle createVehicle(String numberPlate, Owner owner, boolean isCar) {
        try {
            if (!daoVehicleImpl.isExisted(numberPlate) && isCar == true) {
                Vehicle vehicle = new Car(numberPlate);
                vehicle.setOwner(owner);  //Set owner in vehicle
                daoVehicleImpl.insertRecord(vehicle);  //insert in db
                return vehicle;
            }
            if (!daoVehicleImpl.isExisted(numberPlate) && isCar == false) {
                Vehicle vehicle = new Moto(numberPlate);
                vehicle.setOwner(owner);  //Set owner in vehicle
                daoVehicleImpl.insertRecord(vehicle);  ////insert in db
                return vehicle;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Delete vehicle
     */
    public void deleteVehicle(String numberPlate){
        daoVehicleImpl.removeRecord(numberPlate);
    }


    /**
     * Retrieve existed vehicle
     */
    public Vehicle retrieveVehicle(String numberPlate) {
        try {
            if (isExistedVehicle(numberPlate)) {
                return daoVehicleImpl.retrieveRecord(numberPlate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check for if a vehicle is existed in db
     */
    public boolean isExistedVehicle(String numberPlate) {
        try {
            if (daoVehicleImpl.isExisted(numberPlate)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
