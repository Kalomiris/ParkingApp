package com.kalomiris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ConnectionToDB {

    Connection getConnection();

    void close(Connection connection, PreparedStatement prepareStatement);
}

