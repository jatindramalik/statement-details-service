package com.keybank.statement.model;

import lombok.Data;

@Data
public class CustomerDetails {

    private String cardNum;
    private String cvv;
    private String nameOnCard;
    private String expDate;
    private String stDate;
    private String edDate;

}
