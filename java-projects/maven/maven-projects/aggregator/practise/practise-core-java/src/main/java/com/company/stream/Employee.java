package com.company.stream;

import lombok.Data;

@Data
public class Employee {

    private long id;
    private String ename;
    private String department;
    private double salary;

    public Employee() {
    }

    public Employee(long id, String ename, String department, double salary) {
        this.id = id;
        this.ename = ename;
        this.department = department;
        this.salary = salary;
    }
}
