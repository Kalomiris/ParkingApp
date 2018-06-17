package com.kalomiris.input;

import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;
import com.kalomiris.service.VehicleServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputVehicle {
    private static VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
    private static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static String inputPlateEnter() throws IOException {
        String numberPlate;
        do {//check for double edit of vehicle
            System.out.println("Please enter plate of your vehicle: ");
            numberPlate = scanner.readLine();
            if (vehicleServiceImpl.isExistedVehicle(numberPlate)) {
                System.out.println("Wrong input,try again...");
            } else break;
        } while (true);
        return numberPlate;
    }

    public static String inputPlateExit() throws IOException {
        String numberPlate;
        do {//check for double edit of vehicle
            System.out.println("Please enter plate of your vehicle: ");
            numberPlate = scanner.readLine();
            if (!vehicleServiceImpl.isExistedVehicle(numberPlate)) {
                System.out.println("Wrong input,try again...");
            } else break;
        } while (true);
        return numberPlate;
    }

    public static Vehicle inputVehicle(String numberPlate, Owner owner) throws IOException {
        Vehicle vehicle;
        String type;
        do {//Check for type of vehicle, finally input new vehicle in db
            System.out.println("Is car or moto? ");
            type = scanner.readLine();
            if (type.equalsIgnoreCase("car")) {
                vehicle = vehicleServiceImpl.createVehicle(numberPlate, owner, true);
                break;
            } else if (type.equalsIgnoreCase("moto")) {
                vehicle = vehicleServiceImpl.createVehicle(numberPlate, owner, false);
                break;
            } else {
                System.out.println("Wrong input, try again...");
            }
        } while (true);

        return vehicle;
    }


}
