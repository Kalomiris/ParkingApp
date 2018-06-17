package com.kalomiris.dao;

import com.kalomiris.model.Car;
import com.kalomiris.model.Moto;
import com.kalomiris.model.Owner;
import com.kalomiris.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoOwnerImpl implements DaoOwner{
    private ConnectionToDBImpl connectionToDBImpl = new ConnectionToDBImpl();

    public Owner insertRecord(Owner owner) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO mydb.public.owners (id, first_name, last_name) VALUES( ? , ?, ?);";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, owner.getOwnerId());
            preparedStatement.setString(2, owner.getFirstOwnerName());
            preparedStatement.setString(3, owner.getLastOwnerName());
            preparedStatement.execute();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return owner;
    }

    public Owner retrieveRecord(String owner_id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT t.* FROM mydb.public.owners t WHERE t.id = ?;";
        Owner owner = null;
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, owner_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Retrieve data:
            String id = null;
            String firstName = null;
            String lastName = null;
            if (resultSet.next()) {
                id = resultSet.getString("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
            }
            owner = new Owner(firstName, lastName, id);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return owner;
    }


    public void removeRecord(String ownerId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM mydb.public.owners WHERE id = ?;";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, ownerId);
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

    public ArrayList<Vehicle> retrieveVehicleList(String  ownerId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        String query = "SELECT a.number_plate,a.type_vehicle FROM mydb.public.owners t " +
                "INNER JOIN mydb.public.vehicles a ON t.id = a.owner_id WHERE t.id = ?;";

        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            String numberPlate = null;
            Vehicle vehicle = null;
            String type = null;
            while (resultSet.next()){
                numberPlate = resultSet.getString("number_plate");
                type = resultSet.getString("type_vehicle");
                vehicle = ("car".equalsIgnoreCase(type)) ? new Car(numberPlate) : new Moto(numberPlate);
                vehicleList.add(vehicle);
            }
            return vehicleList;
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionToDBImpl.close(conn,preparedStatement);
        }
        return null;
    }

    public boolean isExisted(String id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "SELECT t.id FROM mydb.public.owners t WHERE id = ?;";
        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            if (preparedStatement.executeQuery().next()) {
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
