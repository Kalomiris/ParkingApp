package com.kalomiris.dao;

import com.kalomiris.model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DaoParkpositionImpl implements DaoParkPosition{
    private ConnectionToDBImpl connectionToDBImpl = new ConnectionToDBImpl();

    public ParkPosition insertRecord(ParkPosition parkPosition,int emptyPosition) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "UPDATE mydb.public.parkpositions SET vehicle_id = ?, time = ?, employee_id = ?" +
                "WHERE index = ?;";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, parkPosition.getVehicle().getNumberPlate());
            preparedStatement.setString(2, parkPosition.getMyTime().toString());
            preparedStatement.setString(3, parkPosition.getEmployee().getId());
            preparedStatement.setInt(4,emptyPosition);

            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return parkPosition;
    }

    public void deleteRecord(String vehicle_id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "UPDATE mydb.public.parkpositions t SET vehicle_id = NULL , time = NULL , employee_id = NULL " +
                "WHERE vehicle_id = ?;";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, vehicle_id);

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



    public int EmptyPosition() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT mydb.public.parkpositions.index FROM mydb.public.parkpositions WHERE vehicle_id IS NULL ;";
        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("index");
            }

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionToDBImpl.close(conn,preparedStatement);
        }
        return 0;
    }

    public int counterPosition() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT Count(*) FROM mydb.public.parkpositions WHERE vehicle_id is NOT NULL ;";
        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionToDBImpl.close(conn,preparedStatement);
        }
        return 0;
    }

    public String retrieveAllDetails(String number_Plate){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT t.vehicle_id,a.owner_id,t.time,b.first_name,b.last_name,b.id,a.type_vehicle FROM (" +
                "(mydb.public.parkpositions t  INNER JOIN mydb.public.vehicles a ON t.vehicle_id = a.number_plate) " +
                "INNER JOIN mydb.public.employees b ON t.employee_id = b.id) WHERE t.vehicle_id = ?;";
        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, number_Plate);

            ResultSet resultSet = preparedStatement.executeQuery();
            String numberPlate = null;
            String employeeId = null;
            String firstName = null;
            String lastName = null;
            String type = null;
            String time = null;
            String ownerId = null;
            if (resultSet.next()){
                numberPlate = resultSet.getString("vehicle_id");
                employeeId = resultSet.getString("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                type = resultSet.getString("type_vehicle");
                time = resultSet.getString("time");
                ownerId = resultSet.getString("owner_id");
            }
            return type + " plates: " + numberPlate + "\n" +
                    "Your id is: " + ownerId + "\n" +
                    "Employee: " + firstName + "\t" + lastName + "\t" + "id number: " + employeeId;

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionToDBImpl.close(conn,preparedStatement);
        }

        return null;
    }

    public long retrieveTime(String number_Plate){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "SELECT time FROM mydb.public.parkpositions WHERE vehicle_id = ?;";

        try {
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, number_Plate);
            ResultSet resultSet = preparedStatement.executeQuery();
            String time = null;
            if (resultSet.next()){
                time = resultSet.getString("time");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMMM d HH:mm:ss Z yyyy", Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("EEST"));
            return dateFormat.parse(time).getTime();


        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectionToDBImpl.close(conn,preparedStatement);
        }
        return 0;
    }
}
