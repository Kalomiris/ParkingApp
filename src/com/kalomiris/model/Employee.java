package com.kalomiris.model;

public class Employee {
    private String firstName;
    private String lastName;
    private String id;

    public enum Shift {FIRST, SECOND}

    Shift shift;

    public Employee(String firstName, String lastName, Shift shift, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.shift = shift;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Shift getShift() {
        return shift;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return lastName.hashCode();
    }
}

