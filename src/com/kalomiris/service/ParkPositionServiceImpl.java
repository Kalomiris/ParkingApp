package com.kalomiris.service;

import com.kalomiris.dao.DaoEmployeeImpl;
import com.kalomiris.dao.DaoParkpositionImpl;
import com.kalomiris.model.Employee;
import com.kalomiris.model.Vehicle;
import com.kalomiris.util.ComputeDate;
import com.kalomiris.model.ParkPosition;

public class ParkPositionServiceImpl implements ParkPositionService{

    private DaoParkpositionImpl daoParkpositionImpl = new DaoParkpositionImpl();
    private DaoEmployeeImpl daoEmployeeImpl = new DaoEmployeeImpl();

    /**
     * Insert vehicle, and employee in park position
     */
    public void createParkPosition(Vehicle vehicle){
        int emptyPosition = daoParkpositionImpl.EmptyPosition();
        Employee employee;
        if (ComputeDate.timeForShift(12)) {
            employee = daoEmployeeImpl.retrieveRecord("ak123");
        }else {
            employee = daoEmployeeImpl.retrieveRecord("ak345");
        }
        ParkPosition parkPosition = new ParkPosition(vehicle,employee);
        daoParkpositionImpl.insertRecord(parkPosition,emptyPosition);
    }

    public void deletePosition(String numberPlate){
        daoParkpositionImpl.deleteRecord(numberPlate);
    }

    public void  showDetails(String numberPlate){
        System.out.println(daoParkpositionImpl.retrieveAllDetails(numberPlate));
    }

    public long computeDuration(long time){
        long duration = ComputeDate.Duration(time);
        return duration / 3600000;      //Convert milliseconds in hours.
    }

    public boolean isFull(int i){
        return (daoParkpositionImpl.counterPosition() == i) ? true : false;
    }


    public void printPositionMap(int i){
        int counter = daoParkpositionImpl.counterPosition();
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
