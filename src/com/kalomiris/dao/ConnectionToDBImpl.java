package com.kalomiris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionToDBImpl implements ConnectionToDB{

    private final String url = "";
    private final String user = "";
    private final String password = "";

    /**
     * Connect to the PostgreSQL database
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }


    public void close(Connection connection, PreparedStatement prepareStatement){
        try{
            if(prepareStatement!=null)
                prepareStatement.close();
            connection.close();
        }catch(SQLException se){
        }// do nothing
        try{
            if(connection!=null)
                prepareStatement.close();
            connection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }
}
