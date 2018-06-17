package com.kalomiris.service;

import com.kalomiris.dao.DaoEmployeeImpl;
import com.kalomiris.dao.DaoParkPositionImpl;
import com.kalomiris.model.Employee;
import com.kalomiris.model.Vehicle;
import com.kalomiris.util.ComputeDate;
import com.kalomiris.model.ParkPosition;

public class ParkPositionServiceImpl implements ParkPositionService{

    private DaoParkPositionImpl daoParkPositionImpl = new DaoParkPositionImpl();
    private DaoEmployeeImpl daoEmployeeImpl = new DaoEmployeeImpl();

    /**
     * Insert vehicle, and employee in park position
     */
    public void createParkPosition(Vehicle vehicle){
        int emptyPosition = daoParkPositionImpl.EmptyPosition();
        Employee employee;
        if (ComputeDate.timeForShift(12)) {
            employee = daoEmployeeImpl.retrieveRecord("ak123");
        }else {
            employee = daoEmployeeImpl.retrieveRecord("ak345");
        }
        ParkPosition parkPosition = new ParkPosition(vehicle,employee);
        daoParkPositionImpl.insertRecord(parkPosition,emptyPosition);
    }

    public void deletePosition(String numberPlate){
        daoParkPositionImpl.deleteRecord(numberPlate);
    }

    public void  showDetails(String numberPlate){
        System.out.println(daoParkPositionImpl.retrieveAllDetails(numberPlate));
    }

    public long computeDuration(long time){
        long duration = ComputeDate.Duration(time);
        return duration / 3600000;      //Convert milliseconds in hours.
    }

    public boolean isFull(int i){
        return (daoParkPositionImpl.counterPosition() == i) ? true : false;
    }


    public void printPositionMap(int i){
        int counter = daoParkPositionImpl.counterPosition();
        System.out.println("\n\nThe free position is: " + (i - counter));
        System.out.println("\nMap of parking station:\n");
        System.out.print("=========================================\n");
        for (int j = 0; j < counter; j++) {
            System.out.print(" [X]");
        }
        for (int j = 0; j < i-counter; j++){
            System.out.print(" [ ]");
        }
        System.out.println("\n=========================================\n\n");
    }



}
