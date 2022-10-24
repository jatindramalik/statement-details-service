package com.keybank.statement.model;

import lombok.Data;

@Data
public class StatementDetailsDaoRequest {

    private String clientId;
    private String cardnum;
    private String cvv;
    private String nameOnCard;
    private String expDate;
    private String stDate;
    private String edDate;

    
}
