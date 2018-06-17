package com.kalomiris.dao;

import com.kalomiris.model.Car;
import com.kalomiris.model.Moto;
import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

import java.sql.*;

public class DaoVehicleImpl implements DaoVehicle {
    private ConnectionToDBImpl connectionToDBImpl = new ConnectionToDBImpl();

    public Vehicle insertRecord(Vehicle vehicle) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO mydb.public.vehicles (number_plate,type_vehicle,owner_id) VALUES( ? , ?, ?);";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, vehicle.getNumberPlate());
            String type = (vehicle instanceof Car) ? "Car" : "Moto";  //check for Vehicle type.
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, vehicle.getOwner().getOwnerId());

            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return vehicle;
    }

    public Vehicle retrieveRecord(String number_plate) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Vehicle vehicle = null;
        //  String query = "SELECT t.* FROM mydb.public.vehicles t WHERE t.number_plate = ?";
        String query = "SELECT t.number_plate,t.type_vehicle,t.owner_id,a.first_name,a.last_name FROM mydb.public.vehicles AS t " +
                "INNER JOIN mydb.public.owners AS a ON t.owner_id = a.id " +
                "WHERE t.number_plate = ?;";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, number_plate);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Retrieve data:
            String numberPlate = null;
            String owner_id = null;
            String type = null;
            String firstName = null;
            String lastName = null;
            if (resultSet.next()) {
                numberPlate = resultSet.getString("number_Plate");
                owner_id = resultSet.getString("owner_id");
                type = resultSet.getString("type_vehicle");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
            }
            Owner owner = new Owner(firstName,lastName,owner_id);
            vehicle = ("car".equalsIgnoreCase(type)) ? new Car(numberPlate) : new Moto(numberPlate);
            vehicle.setOwner(owner);
            return vehicle;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return null;
    }

    public void removeRecord(String number_plate) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM mydb.public.vehicles  WHERE number_plate = ?;";
//        String query = "UPDATE mydb.public.vehicles SET number_plate = NULL , type_vehicle = NULL " +
//                "WHERE number_plate = ?;";

        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, number_plate);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
    }

    public boolean isExisted(String numberPlate) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "SELECT t.number_plate FROM mydb.public.vehicles t WHERE t.number_plate = ?;";
        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, numberPlate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return false;
    }
}
