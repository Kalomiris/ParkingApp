package com.kalomiris.dao;

import com.kalomiris.model.Employee;

public interface DaoEmpoloyee {

    Employee insertRecord(Employee employee);

    Employee retrieveRecord(String id);

    boolean removeRecord(String id);
}
