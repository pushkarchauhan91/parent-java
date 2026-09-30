package com.company.condition;

public class EnableDevDataSource implements DataSourceConfig {

    @Override
    public void makeConnection() {
        System.out.println("Connection established to DEV(default) database");

    }
}
