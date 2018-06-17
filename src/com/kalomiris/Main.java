package com.kalomiris;

import com.kalomiris.input.InputOwner;
import com.kalomiris.input.InputVehicle;
import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;
import com.kalomiris.service.OwnerServiceImpl;
import com.kalomiris.service.ParkPositionServiceImpl;
import com.kalomiris.service.VehicleServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static ParkPositionServiceImpl parkPositionServiceImpl = new ParkPositionServiceImpl();
    private static VehicleServiceImpl vehicleServiceImpl = new VehicleServiceImpl();
    private static OwnerServiceImpl ownerServiceImpl = new OwnerServiceImpl();
    private static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        boolean quit = false;

        printActions();

        while (!quit) {

            int action;
            do {
                try {
                    System.out.println("\n...Enter the action: ");
                    action = Integer.parseInt(scanner.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Try again please...\n");
                }
            } while (true);

            switch (action) {
                case 0:
                    quit = true;
                    System.out.println("\n\nShout-down application...");
                    break;
                case 1:
                    enter();
                    break;
                case 2:
                    exitFromGarage();
                    break;
                case 3:
                    parkPositionServiceImpl.isFull(10);
                    break;
                case 4:
                    parkPositionServiceImpl.printPositionMap(10);
                    break;
                case 5:
                    allDetails();
                    break;
                case 6:
                    printActions();
                    break;
                default:
                    System.out.println("Wrong input...\n");
            }
        }
    }

    private static void enter() {
        do {
            try {
                if (parkPositionServiceImpl.isFull(10)){
                    System.out.println("The parking is full!");
                    break;
                }
                String numberPlate = InputVehicle.inputPlateEnter();
                Owner owner = InputOwner.inputOnwer();
                Vehicle vehicle = InputVehicle.inputVehicle(numberPlate,owner);

                //Get in parking...
                owner.addInVehicleList(vehicle);        //insert vehicle in list of model
                parkPositionServiceImpl.createParkPosition(vehicle);
                break;
            } catch (Exception e) {
                System.out.println("Please give your data again...\n\n");
            }
        } while (true);
    }

    private static void exitFromGarage(){
        do {
            try {
                String numberPlate = InputVehicle.inputPlateExit(); //input plate

                //Delete from owner 's list the specific vehicle
                Vehicle vehicle = vehicleServiceImpl.retrieveVehicle(numberPlate);
                Owner owner = vehicle.getOwner();

                //if his vehicle is 0, owner is deleted
                if (owner.getVehicleList().size() <= 1){
                    parkPositionServiceImpl.deletePosition(numberPlate);
                    vehicleServiceImpl.deleteVehicle(numberPlate);
                    ownerServiceImpl.deleteOwner(owner);
                    calculateCharge(owner,vehicle);
                }
                parkPositionServiceImpl.deletePosition(numberPlate);
                owner.getVehicleList().remove(vehicle);
                vehicleServiceImpl.deleteVehicle(numberPlate);
                calculateCharge(owner,vehicle);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Please give data again...\n\n");
            }
        }while (true);
    }



    private static void allDetails(){
        try {
            String numberPlate = InputVehicle.inputPlateExit();
            parkPositionServiceImpl.showDetails(numberPlate);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please give data again...\n\n");
        }

    }

    private static void calculateCharge(Owner owner, Vehicle vehicle){
        double amount = ownerServiceImpl.calculateChargePerVehicle(owner,vehicle);
        System.out.println("The charge is : " + amount);
    }

    private static void printActions() {
        System.out.println("The available action is: (Press 6 for available action)\n" +
                "0- Quit\n" +
                "1- Add a vehicle\n" +
                "2- Exit from garage\n" +
                "3- Is the garage full?\n" +
                "4- Graphics of parking position\n" +
                "5- All details for vehicles and owner\n" +
                "6- The available action");
    }
}
