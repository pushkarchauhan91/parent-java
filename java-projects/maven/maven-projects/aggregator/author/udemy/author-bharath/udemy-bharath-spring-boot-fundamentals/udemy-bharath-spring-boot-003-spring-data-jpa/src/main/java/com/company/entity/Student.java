package com.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    private long id;
    private String name;
    private int marks;
}
