package com.kalomiris.dao;

import com.kalomiris.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEmployeeImpl implements DaoEmpoloyee{

    private ConnectionToDBImpl connectionToDBImpl = new ConnectionToDBImpl();

    public Employee insertRecord(Employee employee) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO mydb.public.employees  (first_name, last_name, shift) VALUES( ? , ?, ?, ?);";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            String shift = ("FIRST".equals(employee.getShift())) ? "FIRST" : "SECOND"; //Validation for employee shift.
            preparedStatement.setString(3, shift);
            preparedStatement.setString(4, employee.getId());
            preparedStatement.execute(query);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return employee;
    }

    public Employee retrieveRecord(String id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT t.* FROM mydb.public.employees t WHERE t.id = ?;";
        Employee employee = null;
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //Retrieve data:
            String firstName = null;
            String lastName = null;
            String shift = null;
            if (resultSet.next()) {
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                shift = resultSet.getString("shift");
            }
            employee = (shift.equalsIgnoreCase("first")) ? new Employee(firstName,lastName, Employee.Shift.FIRST,id) :
                    new Employee(firstName,lastName, Employee.Shift.SECOND,id);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return employee;
    }

    public boolean removeRecord(String id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String query = "UPDATE mydb.public.employees t SET t.first_name = NULL, t.last_name = NULL, t.shift = NULL WHERE t.employee_id = ?;";
        try {
            //Execute a query
            conn = connectionToDBImpl.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.execute(query);
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            connectionToDBImpl.close(conn, preparedStatement);
        }
        return false;
    }
}
