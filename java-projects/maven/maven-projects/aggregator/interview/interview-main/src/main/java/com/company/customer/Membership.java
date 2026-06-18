package com.company.customer;

import lombok.Data;

import java.util.Date;

@Data
public class Membership {

    private long id;
    private String membershipId;
    private Date startDate;
    private Date endDate;
}
